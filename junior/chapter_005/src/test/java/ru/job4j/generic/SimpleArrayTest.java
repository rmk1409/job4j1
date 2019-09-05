package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleArrayTest {
    private SimpleArray<Integer> array;

    @Before
    public void init() {
        array = new SimpleArray<>(10);
    }

    @Test
    public void add() {
        array.add(15);
        assertThat(array.get(0), is(15));
    }

    @Test
    public void set() {
        array.add(15);
        array.set(0, 10);
        assertThat(array.get(0), is(10));
    }

    @Test
    public void remove() {
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.add(19);
        array.add(20);
        array.remove(0);
        array.remove(3);
        int[] actual1 = {16,17,18,20};
        Iterator<Integer> iterator = array.iterator();
        Arrays.stream(actual1)
                .forEach(i->assertThat(iterator.next(), is(i)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void get() {
        array.add(101);
        array.add(102);
        array.add(103);
        assertThat(array.get(1), is(102));
    }

    @Test
    public void iterator() {
        Iterator<Integer> it = array.iterator();
        assertThat(it.hasNext(), is(false));
        array.add(101);
        assertThat(it.hasNext(), is(true));
        array.add(102);
        assertThat(it.next(), is(101));
        assertThat(it.next(), is(102));
        assertThat(it.hasNext(), is(false));
    }
}