package ru.job4j.iterator;

import java.util.*;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class MatrixIterator implements Iterator {
    private int index;
    private List<Integer> list = new ArrayList<>();

    public MatrixIterator(int[][] array) {
        for (int[] cur : array) {
            for (int i : cur) {
                list.add(i);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.list.get(index++);
    }
}
