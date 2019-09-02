package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class CounterTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Counter function = new Counter();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunction() {
        Counter function = new Counter();
        List<Double> result = function.diapason(5, 8, x -> x * x);
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunction() {
        Counter function = new Counter();
        List<Double> result = function.diapason(5, 8, Math::log);
        List<Double> expected = Arrays.asList(1.6D, 1.79D, 1.94D);
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i), closeTo(expected.get(i), 0.01));
        }
    }
}