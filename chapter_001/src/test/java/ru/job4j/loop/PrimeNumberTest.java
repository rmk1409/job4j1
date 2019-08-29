package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PrimeNumberTest {
    private PrimeNumber prime = new PrimeNumber();

    @Test
    public void when5() {
        int count = prime.calc(5);
        assertThat(count, is(3));
    }

    @Test
    public void when11() {
        int count = prime.calc(11);
        assertThat(count, is(5));
    }

    @Test
    public void when1() {
        int count = prime.calc(2);
        assertThat(count, is(1));
    }
}