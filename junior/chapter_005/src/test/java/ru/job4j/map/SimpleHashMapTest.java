package ru.job4j.map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 06.09.2019
 */
public class SimpleHashMapTest {
    private SimpleHashMap<String, String> map;

    @Before
    public void setUp() {
        this.map = new SimpleHashMap<>();
    }

    @Test
    public void insert() {
        Iterator<SimpleHashMap.Node<String, String>> iterator = this.map.iterator();
        assertFalse(iterator.hasNext());
        this.map.insert("First", "Value1");
        this.map.insert("First", "Value2");
        iterator = this.map.iterator();
        SimpleHashMap.Node<String, String> node = iterator.next();
        assertThat(node.getKey(), is("First"));
        assertThat(node.getValue(), is("Value1"));
        assertFalse(iterator.hasNext());
    }

    @Test
    @Ignore
    public void checkSizeIncease() {
        IntStream.range(0, 12)
                .forEach(i -> this.map.insert("key " + i, "value " + i));
    }

    @Test
    public void get() {
        this.map.insert("Key 1", "Value");
        assertThat(this.map.get("Key 1"), is("Value"));
    }

    @Test
    public void delete() {
        this.map.insert("Key 1", "Value");
        this.map.delete("Key 1");
        assertNull(this.map.get("Key 1"));
    }
}