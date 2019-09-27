package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Реализуйте шаблон Producer Consumer.
 * <p>
 * Для этого вам необходимо реализовать собственную версию bounded blocking queue. Это блокирующая очередь, ограниченная по размеру.
 * В данном шаблоне Producer помещает данные в очередь, а Consumer извлекает данные из очереди.
 * Если очередь заполнена полностью, то при попытке добавления поток Producer блокируется, до тех пор пока Consumer не извлечет очередные данные, т.е. в очереди появится свободное место.
 * И наоборот если очередь пуста поток Consumer блокируется, до тех пор пока Producer не поместит в очередь данные.
 * <p>
 * В задании нельзя использовать потокобезопасные коллекции реализованные в JDK. Ваша задача используя, wait/notify реализовать блокирующую очередь.
 * <p>
 * Давайте сделаем каркас нашего приложения.
 * Producer Consumer - по сути это обычные нити.
 * Для того чтобы нить перевести в ждущее состояние необходимо в ее процессе вызвать метод wait() для объекта монитора.
 * Для того чтобы разбудить нить, нужно, чтобы другая нить вызвала у объекта монитора метод notify().
 * <p>
 * Теперь давайте перейдем к созданию нашей блокирующей очереди.
 * Создадим класс SimpleBlockingQueue
 * Каждой нити нужно передать объект:
 * new SimpleBlockingQueue<Integer>() Этот объект будет общим ресурсом между этими нитями.
 * Метод poll() должен вернуть объект из внутренней коллекции. Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания.
 * Важный момент, когда нить переводить в состояние ожидания, то она отпускает объект монитор и другая нить тоже может выполнить этот метод.
 * <p>
 * Рассмотрим диаграмму выполнения этой программы:
 * То есть у нас может быть бесконечное число пользователей этой очереди.
 * Теперь давайте посмотрим диаграмму когда у нас есть производитель данных:
 * <p>
 * Задание.
 * 1. Реализовать методы poll() и offer().
 * 2. Написать тесты. В тестах должны быть две нити: одна производитель, другая потребитель.
 * <p>
 * Через методы join() добиться последовательного выполнение программы.
 * <p>
 * Created by roman.pogorelov on 26.09.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static final Logger LOG = Logger.getLogger(SimpleBlockingQueue.class.getName());
    @GuardedBy("this")
    private Queue<T> queue;
    private int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    /**
     * Puts the value in the queue.
     *
     * @param value is used to put
     */
    public synchronized void offer(T value) {
        while (this.capacity == this.queue.size()) {
            LOG.info("Queue is full, producer please wait.");
            try {
                this.wait();
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        this.queue.offer(value);
        LOG.info(String.format("Value in the queue - %d", value));
        this.notify();
    }

    /**
     * Gets a value from the queue.
     *
     * @return a value
     */
    public synchronized T poll() {
        while (this.queue.size() == 0) {
            LOG.info(String.format("Queue is empty, consumer(%d) please wait.", Thread.currentThread().hashCode()));
            try {
                this.wait();
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        T element = this.queue.poll();
        LOG.info(String.format("Got value from the queue - %d", element));
        return element;
    }
}

class Consumer implements Runnable {
    private static final Logger LOG = Logger.getLogger(Consumer.class.getName());
    private SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            LOG.info(String.format("Consumer(%d) is trying to get a new value", this.hashCode()));
            Integer element = this.queue.poll();
            LOG.info(String.format("Consumer(%d) got the value - %d", this.hashCode(), element));
            Thread.yield();
        }
    }
}

class Producer implements Runnable {
    private static final Logger LOG = Logger.getLogger(Producer.class.getName());
    private SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            LOG.info(String.format("Producer is trying to put - %d", i));
            this.queue.offer(i);
            Thread.yield();
        }
    }
}