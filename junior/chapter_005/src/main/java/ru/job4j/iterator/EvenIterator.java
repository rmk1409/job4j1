package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class EvenIterator implements Iterator {
    private int index = 0;
    private int[] array;

    public EvenIterator(int[] array) {
        this.array = array;
        this.findEven();
    }

    private void findEven() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                index = i;
                result = true;
                break;
            }
        }
        if (!result) {
            index = -1;
        }
    }

    @Override
    public boolean hasNext() {
        return this.index != -1;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = this.array[index++];
        this.findEven();
        return result;
    }
}
