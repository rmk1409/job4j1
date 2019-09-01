package ru.job4j.chapter001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class JoinerTest {
    private Joiner joiner = new Joiner();

    @Test
    public void joinFirstEmpty() {
        int[] first = {};
        int[] second = {1, 5, 7, 9, 11};
        int[] actual = {1, 5, 7, 9, 11};
        assertThat(actual, is(joiner.join(first, second)));
    }

    @Test
    public void joinSecondEmpty() {
        int[] first = {1, 5, 7, 9, 11};
        int[] second = {};
        int[] actual = {1, 5, 7, 9, 11};
        assertThat(actual, is(joiner.join(first, second)));
    }

    @Test
    public void joinDifferentSize() {
        int[] first = {1, 5, 7, 9, 11};
        int[] second = {1, 2, 3, 47};
        int[] actual = {1, 1, 2, 3, 5, 7, 9, 11, 47};
        assertThat(actual, is(joiner.join(first, second)));
    }
}