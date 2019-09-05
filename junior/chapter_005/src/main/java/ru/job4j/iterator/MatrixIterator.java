package ru.job4j.iterator;

import java.util.*;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class MatrixIterator implements Iterator {
    private int horizontal;
    private int vertical;
    private int[][] array;

    public MatrixIterator(int[][] array) {
        this.array = array;
        this.findNext();
    }

    private void findNext() {
        boolean result = false;
        for (int i = horizontal; i < array.length; i++) {
            if (vertical < array[i].length) {
                result = true;
                this.horizontal = i;
                break;
            } else {
                vertical = 0;
            }
        }
        if (!result) {
            this.horizontal = -1;
        }
    }

    @Override
    public boolean hasNext() {
        return this.horizontal != -1;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = this.array[this.horizontal][this.vertical++];
        this.findNext();
        return result;
    }
}
