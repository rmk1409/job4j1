package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

public class FitTest {
    private static final double DELTA = 0.1;

    @Test
    public void manWeight() {
        double in = 100;
        double expected = 0;

        Assert.assertEquals(expected, Fit.manWeight(in), DELTA);
    }

    @Test
    public void womanWeight() {
        double in = 200;
        double expected = 103.5;

        Assert.assertEquals(expected, Fit.womanWeight(in), DELTA);
    }
}
