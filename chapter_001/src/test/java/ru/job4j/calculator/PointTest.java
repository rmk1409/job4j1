package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class PointTest {
    @Test
    public void distance() {
        Assert.assertEquals(2.8, new Point(0, 1).distance(new Point(2, 3)), 0.1);
        Assert.assertEquals(7.3, new Point(0, 10).distance(new Point(2, 3)), 0.1);
        Assert.assertEquals(20.1, new Point(0, 1).distance(new Point(20, 3)), 0.1);
        Assert.assertEquals(21.1, new Point(0, 10).distance(new Point(20, 3)), 0.1);
    }
}
