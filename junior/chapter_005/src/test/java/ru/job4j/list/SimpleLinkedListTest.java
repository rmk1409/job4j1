package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteException() {
        IntStream.range(0, 4).forEach(i -> list.delete());
    }

    @Test
    public void delete() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.delete(), is(1));
        assertThat(list.getSize(), is(0));
    }
}