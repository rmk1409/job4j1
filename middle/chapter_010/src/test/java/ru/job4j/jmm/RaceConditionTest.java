package ru.job4j.jmm;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * TODO Description
 * Created by roman.pogorelov on 25.09.2019
 */
public class RaceConditionTest {

    @Test
    @Ignore // Works in 99.99%
    public void getRaceCondition() {
        int n = 1_000;
        assertNotEquals(new RaceCondition().getRaceCondition(n), n);
    }
}