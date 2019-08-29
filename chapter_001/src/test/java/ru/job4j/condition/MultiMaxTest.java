package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MultiMaxTest {
    private MultiMax check = new MultiMax();

    @Test
    public void whenFirstMax() {
        int result = check.max(10, 4, 2);
        assertThat(result, is(10));
    }

    @Test
    public void whenSecondMax() {
        int result = check.max(1, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdMax() {
        int result = check.max(1, 4, 20);
        assertThat(result, is(20));
    }

    @Test
    public void whenAllTheSame() {
        int result = check.max(4, 4, 4);
        assertThat(result, is(4));
    }
}