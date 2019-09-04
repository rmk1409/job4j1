package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class MatrixIterator implements Iterator {
    private int index;
    private int[][] array;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < Arrays.stream(this.array)
                .flatMapToInt(Arrays::stream).count();
    }

    @Override
    public Integer next() {
        return Arrays.stream(this.array)
                .flatMapToInt(Arrays::stream)
                .skip(index++)
                .findFirst().getAsInt();
    }
}
