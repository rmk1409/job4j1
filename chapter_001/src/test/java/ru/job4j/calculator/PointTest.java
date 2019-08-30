package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void distance() {
        Assert.assertEquals(2.8, new Point(0, 1).distance(new Point(2, 3)), 0.1);
        Assert.assertEquals(7.3, new Point(0, 10).distance(new Point(2, 3)), 0.1);
        Assert.assertEquals(20.1, new Point(0, 1).distance(new Point(20, 3)), 0.1);
        Assert.assertEquals(21.1, new Point(0, 10).distance(new Point(20, 3)), 0.1);
    }

    @Test
    public void distance3d() {
        assertThat(10.24, closeTo(new Point(7, 4, 3).distance3d(new Point(17, 6, 2)), 0.01));
    }
}
