package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class DynamicSizeArrayTest {
    private DynamicSizeArray<Integer> list;

    @Before
    public void init() {
        list = new DynamicSizeArray<>(4);
        list.add(100);
        list.add(101);
        list.add(102);
    }

    @Test
    public void add() {
        Integer[] expected = {100, 101, 102, null};
        assertThat(list.getContainer(), is(expected));
        assertThat(list.getContainer().length, is(4));
        list.add(103);
        list.add(104);
        assertThat(list.getContainer().length, is(6));
    }

    @Test
    public void get() {
        assertThat(list.get(0), is(100));
        assertThat(list.get(1), is(101));
        assertThat(list.get(2), is(102));
    }

    @Test(expected = NoSuchElementException.class)
    public void getException() {
        list.get(3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(103);
        iterator.hasNext();
    }

    @Test
    public void iterator() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next(), is(100));
        assertThat(iterator.next(), is(101));
        assertThat(iterator.next(), is(102));
        iterator = list.iterator();
        assertThat(iterator.next(), is(100));
    }
}