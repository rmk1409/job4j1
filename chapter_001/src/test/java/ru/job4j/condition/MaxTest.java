package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MaxTest {
    private Max max = new Max();

    @Test
    public void whenMax1To2Then2() {
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax2To1Then1() {
        int result = max.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenTheSame() {
        int result = max.max(1, 1);
        assertThat(result, is(1));
    }

    @Test
    public void when3numbers() {
        assertThat(max.max(1, 2, 3), is(3));
    }

    @Test
    public void when4numbers() {
        assertThat(max.max(1, 4, 2, 3), is(4));
    }
}
