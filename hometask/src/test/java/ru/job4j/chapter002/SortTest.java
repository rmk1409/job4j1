package ru.job4j.chapter002;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class SortTest {
    private String[] data;

    @Before
    public void init() {
        data = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
    }

    @Test
    public void sortAsc() {
        List<String> expected = Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2");
        List<String> actual = new Sort().sortAsc(this.data);
        assertThat(actual, is(expected));
    }

    @Test
    public void descAsc() {
        List<String> expected = List.of("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1");
        List<String> actual = new Sort().sortDesc(this.data);
        assertThat(actual, is(expected));
    }
}