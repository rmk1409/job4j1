package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class EvenIterator implements Iterator {
    private int index;
    private int[] array;

    public EvenIterator(int[] array) {
        this.array = Arrays.stream(array)
                .filter(i -> i % 2 == 0)
                .toArray();
    }

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        return this.array[index++];
    }
}
