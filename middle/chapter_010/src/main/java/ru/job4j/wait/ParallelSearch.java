package ru.job4j.wait;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * В этом задании нужно разработать механизм остановки потребителя, когда производитель закончил свою работу.
 * <p>
 * Представим утилиту по поиску текста в файловой системе.
 * Одна нить ищет файлы с подходящим именем. Вторая нить берет эти файлы и читает.
 * Это схема хорошо описывается шаблон Producer Consumer. Однако есть один момент.
 * Когда первая нить заканчивает свою работу, потребители переходят в режим wait.
 * И утилита работает вечно.
 * Давайте составим упрощенный код такой программы.
 * <p>
 * Если мы запустим этот код. то на консоли мы увидим. что нить производитель закончила работу, а нить потребитель продолжает ждать событий.
 * Ваша задача изменить код, так. что бы потребитель завершал свою работу.
 * Код нужно изменить в методе main.
 * <p>
 * Created by roman.pogorelov on 27.09.2019
 */
public class ParallelSearch {
    private static final Logger LOG = Logger.getLogger(ParallelSearch.class.getName());

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            LOG.log(Level.SEVERE, e.getMessage(), e);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            LOG.log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    consumer.interrupt();
                }

        ).start();
    }
}