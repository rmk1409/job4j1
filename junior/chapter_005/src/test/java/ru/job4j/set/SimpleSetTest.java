package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleSetTest {
    private SimpleSet<Integer> set;

    @Before
    public void init() {
        set = new SimpleSet<>();
    }

    @Test
    public void add() {
        assertFalse(set.iterator().hasNext());
        set.add(100);
        set.add(100);
        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void add2() {
        set.add(1);
        set.add(null);
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), is(1));
        assertNull(iterator.next());
        assertThat(iterator.next(), is(2));
        assertFalse(iterator.hasNext());
    }
}