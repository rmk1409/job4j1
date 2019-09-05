package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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

    @Test
    public void hasCycleTrue() {
        SimpleLinkedList.Node first = new SimpleLinkedList.Node(1);
        SimpleLinkedList.Node second = new SimpleLinkedList.Node(2);
        SimpleLinkedList.Node third = new SimpleLinkedList.Node(3);
        SimpleLinkedList.Node fourth = new SimpleLinkedList.Node(4);
        SimpleLinkedList.Node fifth = new SimpleLinkedList.Node(5);
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        fifth.setNext(first);
        assertTrue(list.hasCycle(first));
    }

    @Test
    public void hasCycleInTheMiddleTrue() {
        SimpleLinkedList.Node first = new SimpleLinkedList.Node(1);
        SimpleLinkedList.Node second = new SimpleLinkedList.Node(2);
        SimpleLinkedList.Node third = new SimpleLinkedList.Node(3);
        SimpleLinkedList.Node fourth = new SimpleLinkedList.Node(4);
        SimpleLinkedList.Node fifth = new SimpleLinkedList.Node(5);
        first.setNext(second);
        second.setNext(third);
        third.setNext(second);
        fourth.setNext(fifth);
        assertTrue(list.hasCycle(first));
    }

    @Test
    public void hasCycleFalse() {
        SimpleLinkedList.Node first = new SimpleLinkedList.Node(1);
        SimpleLinkedList.Node second = new SimpleLinkedList.Node(2);
        SimpleLinkedList.Node third = new SimpleLinkedList.Node(3);
        SimpleLinkedList.Node fourth = new SimpleLinkedList.Node(4);
        SimpleLinkedList.Node fifth = new SimpleLinkedList.Node(5);
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        assertFalse(list.hasCycle(first));
    }
}