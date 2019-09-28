package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Пул - это хранилища для ресурсов, которые можно переиспользовать.
 * Клиент берет ресурс из пула, выполняет свою работу и возвращается обратно в пул.
 * <p>
 * 1. Инициализация пула должна быть по количеству ядер в системе.
 * int size = Runtime.getRuntime().availableProcessors()
 * В каждую нить передается блокирующая очередь tasks.
 * Количество нитей всегда одинаковое и равно size.
 * В методе run мы должны получить задачу из очереди tasks.
 * tasks - это блокирующая очередь. Если в очереди нет элементов, то нить переводиться в состоянии waiting.
 * Когда приходит новая задача, всем нитям в состоянии waiting посылается сигнал проснуться и начать работу.
 * <p>
 * 2. Создать метод work(Runnable job). - этот метод должен добавлять задачи в блокирующую очередь tasks.
 * <p>
 * Created by roman.pogorelov on 27.09.2019
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        IntStream.range(0, Runtime.getRuntime().availableProcessors() + 1)
                .forEach(i -> threads.add(new Thread(new Task(tasks))));
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        this.tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool p = new ThreadPool();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            p.work(() -> System.out.println(finalI));
        }
        Thread.sleep(1_000);
        p.shutdown();
    }
}

class Task implements Runnable {
    private static final Logger LOG = Logger.getLogger(Task.class.getName());
    private final SimpleBlockingQueue<Runnable> tasks;

    public Task(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        try {
            while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                Runnable element = this.tasks.poll();
                new Thread(element).start();
            }
        } catch (InterruptedException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}