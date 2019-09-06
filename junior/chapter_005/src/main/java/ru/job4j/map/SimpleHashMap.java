package ru.job4j.map;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 * <p>
 * Реализовывать итератор.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение. Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 * <p>
 * Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.
 * <p>
 * Created by roman.pogorelov on 06.09.2019
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] container;
    private double loadFactor = 0.75;
    private int modCount;
    private int size;

    public SimpleHashMap() {
        this.container = (Node<K, V>[]) new Node[10];
    }


    public static void main(String[] args) {
        IntStream.range(0, 11).forEach(System.out::println);
    }

    public boolean insert(K key, V value) {
        boolean result = false;

        int index = this.getIndex(key);
        if (this.container[index] == null) {
            this.container[index] = new Node<>(key, value);
            this.modCount++;
            this.size++;
            this.checkContainer();
            result = true;
        }

        return result;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % (this.container.length);
    }

    private void checkContainer() {
        if (this.size >= this.loadFactor * this.container.length) {
            this.size = 0;
            Node<K, V>[] old = this.container;
            this.container = (Node<K, V>[]) new Node[old.length * 2];
            Arrays.stream(old)
                    .filter(Objects::nonNull)
                    .forEach(n -> this.insert(n.key, n.value));
        }
    }

    public V get(K key) {
        Node<K, V> node = container[this.getIndex(key)];
        V result = null;
        if (node != null) {
            result = node.value;
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = this.getIndex(key);
        if (this.container[index] != null) {
            this.container[index] = null;
            this.modCount++;
            this.size--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private int itModCount;
            private int itIndex;

            {
                this.itModCount = modCount;
                this.findNext();
            }

            @Override
            public boolean hasNext() {
                this.checkModCount();
                return this.itIndex != -1 && this.itIndex < container.length;
            }

            @Override
            public Node<K, V> next() {
                this.checkModCount();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = container[itIndex++];
                this.findNext();
                return result;
            }

            private void findNext() {
                boolean result = false;
                for (int i = itIndex; i < container.length; i++) {
                    if (Objects.nonNull(container[i])) {
                        this.itIndex = i;
                        result = true;
                        break;
                    }
                }
                if (!result) {
                    itIndex = -1;
                }
            }

            private void checkModCount() {
                if (this.itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
