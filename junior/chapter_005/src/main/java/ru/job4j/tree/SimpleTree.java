package ru.job4j.tree;

import java.util.Optional;

/**
 * TODO Description
 * Created by roman.pogorelov on 06.09.2019
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * @param parent parent.
     * @param child  child.
     * @return
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}