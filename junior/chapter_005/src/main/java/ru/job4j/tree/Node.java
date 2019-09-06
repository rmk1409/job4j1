package ru.job4j.tree;

import java.util.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 06.09.2019
 */
public class Node<E extends Comparable<E>> {
    private final E value;
    private final List<Node<E>> children = new ArrayList<>();

    public Node(final E value) {
        this.value = value;
    }

    /**
     *
     * @param child
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return value;
    }
}