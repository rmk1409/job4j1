package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest {
    private Factorial factorial = new Factorial();

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        assertThat(1, is(factorial.calc(0)));
    }

    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        assertThat(120, is(factorial.calc(5)));
    }
}