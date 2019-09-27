package ru.job4j.wait;

import org.junit.Test;

/**
 * Написать тесты. В тестах должны быть две нити: одна производитель, другая потребитель.
 * <p>
 * Через методы join() добиться последовательного выполнение программы.
 * <p>
 * Created by roman.pogorelov on 26.09.2019
 */
public class SimpleBlockingQueueTest {
    @Test
    public void serialPerformance() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Consumer cons1 = new Consumer(queue);
        Consumer cons2 = new Consumer(queue);
        Producer prod = new Producer(queue);
        Thread t1 = new Thread(cons1);
        Thread t2 = new Thread(cons2);
        Thread t3 = new Thread(prod);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}