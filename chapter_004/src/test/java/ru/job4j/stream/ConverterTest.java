package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class ConverterTest {

    @Test
    public void convert() {
        Integer[][] in = {
                {1, 2},
                {3, 4, 5}
        };
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertThat(expected, is(new Converter().convert(in)));
    }
}