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
    }

    @Override
    public boolean hasNext() {
        return this.horizontal < this.array.length
                && this.vertical < this.array[horizontal].length;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = this.array[this.horizontal][this.vertical++];
        if (this.vertical == this.array[horizontal].length) {
            this.vertical = 0;
            this.horizontal++;
        }
        return result;
    }
}
