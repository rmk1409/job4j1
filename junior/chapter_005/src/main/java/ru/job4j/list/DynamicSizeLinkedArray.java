package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class DynamicSizeLinkedArray<E> implements Iterable<E> {
    private int modCount;
    private int size;
    private Node<E> head;

    public DynamicSizeLinkedArray() {
    }

    public void add(E element) {
        this.modCount++;
        this.size++;
        Node<E> node = new Node<>(element);
        if (this.head != null) {
            node.next = this.head;
        }
        this.head = node;
    }

    public E get(int index) {
        if (this.size <= index) {
            throw new NoSuchElementException();
        }
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
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
                return this.itIndex < size;
            }

            @Override
            public E next() {
                checkModCount();
                return get(itIndex++);
            }

            private void checkModCount() {
                if (this.itCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}
