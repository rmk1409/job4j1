package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {
    private Square square = new Square();

    @Test
    public void whenBound3Then149() {
        int bound = 3;
        int[] rst = square.calculate(bound);
        int[] expect = new int[]{1, 4, 9};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenBound4Then14916() {
        int bound = 4;
        int[] rst = square.calculate(bound);
        int[] expect = new int[]{1, 4, 9, 16};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenBound1Then1() {
        int bound = 1;
        int[] rst = square.calculate(bound);
        int[] expect = new int[]{1};
        assertThat(rst, is(expect));
    }
}