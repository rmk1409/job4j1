package ru.job4j.wait;

/**
 * TODO Description
 * Created by roman.pogorelov on 28.09.2019
 */
public class NotifyTest implements Runnable {
    @Override
    public void run() {
        synchronized (NotifyTest.class) {
            System.out.println("Waiting: " + this);

            try {
                NotifyTest.class.wait();
            } catch (InterruptedException ex) {
                return;
            }

            System.out.println("Notified: " + this);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(new NotifyTest()).start();
        }

        Thread.sleep(1000L); // Let them go into wait ()

        System.out.println("Doing notify ()");

        synchronized (NotifyTest.class) {
            NotifyTest.class.notify();
        }

        Thread.sleep(1000L); // Let them print their messages

        System.out.println("Doing notifyAll ()");

        synchronized (NotifyTest.class) {
            NotifyTest.class.notifyAll();
        }
    }
}