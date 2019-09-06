package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 06.09.2019
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iterator() {
        Tree<String> tree = new Tree<>("C");
        tree.add("C", "B");
        tree.add("C", "D");
        tree.add("C", "F");
        tree.add("B", "A");
        tree.add("F", "G");
        tree.add("F", "I");
        tree.add("I", "H");
        Iterator<String> iter = tree.iterator();
        assertThat(iter.next(), is("C"));
        assertThat(iter.next(), is("B"));
        assertThat(iter.next(), is("D"));
        assertThat(iter.next(), is("F"));
        assertThat(iter.next(), is("A"));
        assertThat(iter.next(), is("G"));
        assertThat(iter.next(), is("I"));
        assertThat(iter.next(), is("H"));
        assertFalse(iter.hasNext());
    }
}