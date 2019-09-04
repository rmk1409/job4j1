package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private List<Iterator<Integer>> list = new ArrayList<>();

            {
                while (it.hasNext()) {
                    list.add(it.next());
                }
            }

            @Override
            public boolean hasNext() {
                return list.stream().anyMatch(Iterator::hasNext);
            }

            @Override
            public Integer next() {
                return list.stream()
                        .filter(Iterator::hasNext)
                        .findFirst().orElseThrow().next();
            }
        };
    }
}
