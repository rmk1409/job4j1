package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleSetTest {
    private SimpleSet<String> set = new SimpleSet<>();

    @Test
    public void add() {
        assertFalse(set.iterator().hasNext());
        set.add("first");
        set.add("first");
        Iterator<String> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}