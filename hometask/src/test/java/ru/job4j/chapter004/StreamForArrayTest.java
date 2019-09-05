package ru.job4j.chapter004;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class StreamForArrayTest {

    @Test
    public void work() {
        int[] in = {1, 2, 3, 4, 5, 6};
        assertThat(new StreamForArray().work(in), is(56));
    }
}