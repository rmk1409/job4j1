package ru.job4j.jmm;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Создать пример иллюстрирующий проблемы, которые могут случиться при использовании многопоточности.
 * <p>
 * В ответе к задаче напишите текстом в комментарии. какие проблемы есть и почему они возникают в вашем коде.
 * <p>
 * Created by roman.pogorelov on 25.09.2019
 */
public class RaceCondition {
    private static final Logger LOG = Logger.getLogger(RaceCondition.class.getName());

    public int getRaceCondition(int n) {
        Box box = new Box(0);
        Thread[] threads = new Thread[n];
        IntStream.range(0, n)
                .forEach(i -> threads[i] = new Increaser(box));
        IntStream.range(0, n)
                .forEach(i -> threads[i].start());
        IntStream.range(0, n)
                .forEach(i -> {
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        LOG.log(Level.SEVERE, e.getMessage(), e);
                    }
                });
        System.out.println(box.getValue());
        return box.getValue();
    }
}

class Box {
    private int value;

    public Box(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class Increaser extends Thread {
    private Box box;

    public Increaser(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        int value = box.getValue();
        Thread.yield();
        value++;
        box.setValue(value);
    }
}