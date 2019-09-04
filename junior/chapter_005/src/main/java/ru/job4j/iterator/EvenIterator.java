package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class EvenIterator implements Iterator {
    private int index;
    private int[] array;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return Arrays.stream(this.array)
                .skip(this.index)
                .anyMatch(i -> i % 2 == 0);
    }

    @Override
    public Integer next() {
        return Arrays.stream(this.array)
                .skip(this.index)
                .filter(i -> {
                    this.index++;
                    return i % 2 == 0;
                })
                .findFirst()
                .getAsInt();
    }
}
