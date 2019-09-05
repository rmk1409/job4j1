package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by roman.pogorelov on 04.09.2019
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> composite) {
        return new Iterator<>() {
            private Iterator<Integer> iterator;

            {
                this.checkIterator();
            }

            private void checkIterator(){
                if (this.iterator == null || !this.iterator.hasNext()) {
                    while (composite.hasNext()) {
                        Iterator<Integer> current = composite.next();
                        if (current.hasNext()) {
                            this.iterator = current;
                            break;
                        }
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return this.iterator != null && this.iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                Integer result = this.iterator.next();
                this.checkIterator();
                return result;
            }
        };
    }
}
