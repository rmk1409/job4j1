package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class DynamicSizeArray<E> implements Iterable<E> {
    private int index;
    private int modCount;

    public Object[] getContainer() {
        return container;
    }

    private Object[] container;

    public DynamicSizeArray(int size) {
        this.container = new Object[size];
    }

    public void add(E element) {
        this.modCount++;
        if (this.index == this.container.length) {
            Object[] tmp = new Object[(int) (this.container.length * 1.5)];
            System.arraycopy(this.container, 0, tmp, 0, index);
            this.container = tmp;
        }
        this.container[index++] = element;
    }

    public E get(int index) {
        if (this.index <= index) {
            throw new NoSuchElementException();
        }
        return (E) this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int itCount;
            private int itIndex;

            {
                this.itCount = modCount;
            }

            @Override
            public boolean hasNext() {
                checkModCount();
                return this.itIndex < index;
            }

            @Override
            public E next() {
                checkModCount();
                return (E) container[this.itIndex++];
            }

            private void checkModCount() {
                if (this.itCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
