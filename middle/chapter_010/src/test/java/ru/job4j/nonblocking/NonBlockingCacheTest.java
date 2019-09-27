package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

/**
 * TODO Description
 * Created by roman.pogorelov on 27.09.2019
 */
public class NonBlockingCacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        class ThreadForNBCache implements Runnable {
            private NonBlockingCache cache;

            public ThreadForNBCache(NonBlockingCache cache) {
                this.cache = cache;
            }

            @Override
            public void run() {
                try {
                    this.cache.update(new Base(1, "name2"));
                } catch (OptimisticException e) {
                    ex.set(e);
                }
            }
        }
        NonBlockingCache nCache = new NonBlockingCache();
        nCache.add(new Base(1, "name1"));
        Thread thr1 = new Thread(new ThreadForNBCache(nCache));
        Thread thr2 = new Thread(new ThreadForNBCache(nCache));
        thr1.start();
        thr2.start();
        thr1.join();
        thr2.join();
        Assert.assertThat(ex.get().getMessage(), is("Optimistic lock is working"));
    }
}