package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FitnessTest {
    private Fitness fit = new Fitness();

    @Test
    public void whenIvanGreatNik() {
        int days = fit.calc(95, 90);
        assertThat(days, is(0));
    }

    @Test
    public void whenIvanLessByOneNik() {
        int days = fit.calc(90, 95);
        assertThat(days, is(1));
    }

    @Test
    public void whenIvanLessByFewNik() {
        int days = fit.calc(50, 90);
        assertThat(days, is(2));
    }
}