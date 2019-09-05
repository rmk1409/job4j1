package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private int index = 0;
            private List<Integer> list = new ArrayList<>();

            {
                while (it.hasNext()) {
                    Iterator<Integer> inner = it.next();
                    while (inner.hasNext()) {
                        list.add(inner.next());
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return this.index < list.size();
            }

            @Override
            public Integer next() {
                if (index == list.size()) {
                    throw new NoSuchElementException();
                }
                return list.get(index++);
            }
        };
    }
}
