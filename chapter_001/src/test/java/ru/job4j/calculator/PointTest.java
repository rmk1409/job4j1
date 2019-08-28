package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class PointTest {
    @Test
    public void distance(){
        Assert.assertEquals(2.8, Point.distance(0, 1, 2, 3), 0.1);
        Assert.assertEquals(7.3, Point.distance(0, 10, 2, 3), 0.1);
        Assert.assertEquals(20.1, Point.distance(0, 1, 20, 3), 0.1);
        Assert.assertEquals(21.1, Point.distance(0, 10, 20, 3), 0.1);
    }
}
