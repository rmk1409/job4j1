package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {

    @Test
    public void maxFirst() {
        assertThat(SqMax.max(10, 9, 8, 7), is(10));
    }

    @Test
    public void maxSecond() {
        assertThat(SqMax.max(10, 19, 8, 7), is(19));
    }

    @Test
    public void maxThird() {
        assertThat(SqMax.max(10, 9, 18, 7), is(18));
    }

    @Test
    public void maxFourth() {
        assertThat(SqMax.max(10, 9, 8, 17), is(17));
    }

    @Test
    public void maxTheSame() {
        assertThat(SqMax.max(10, 10, 10, 10), is(10));
    }
}