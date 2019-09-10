package ru.job4j.tree;

import java.util.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 06.09.2019
 */
public class Tree<T extends Comparable<T>> implements SimpleTree<T> {
    private Node<T> root;
    private int modCount;

    public Tree(T root) {
        this.root = new Node<>(root);
    }

    /**
     * В дереве не могут быть дубликатов, т.е. никакие узлы в дереве не должны иметь двух одинаковых дочерних узлов.
     * Обязательно реализуйте итератор.
     * <p>
     * метод add - Должен находить элемент parent в дереве и добавлять в него дочерний элемент.
     * <p>
     * node.children.add(child);
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    @Override
    public boolean add(T parent, T child) {
        boolean result = false;
        boolean uniqueness = findBy(child).isEmpty();
        if (uniqueness) {
            Optional<Node<T>> parentNode = findBy(parent);
            Node<T> childNode = new Node<>(child);
            if (parentNode.isPresent()) {
                parentNode.get().leaves().add(childNode);
            } else {
                if (this.root != null) {
                    childNode.add(this.root);
                }
                this.root = childNode;
            }
            this.modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int itModCount = modCount;
            private Queue<Node<T>> queue = new LinkedList<>();

            {
                if (root != null) {
                    this.queue.offer(root);
                }
            }

            @Override
            public boolean hasNext() {
                this.checkModCount();
                return this.queue.size() != 0;
            }

            @Override
            public T next() {
                this.checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> poll = this.queue.poll();
                poll.leaves()
                        .forEach(this.queue::offer);
                return poll.getValue();
            }

            private void checkModCount() {
                if (this.itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * алгоритм поиска в ширину
     * <p>
     * Смысл этого алгоритма в следующем.
     * Мы берем очередь и добавляем первый элемент дерева - это корень.
     * Дальше, если корень не наш элемент мы добавляем все элементы корня.
     * И так для каждого элемента.
     *
     * @param value
     * @return
     */
    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> result = Optional.empty();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<T> el = queue.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                queue.offer(child);
            }
        }
        return result;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<T>> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
            while (result && !queue.isEmpty()) {
                Node<T> el = queue.poll();
                if (el.leaves().size() > 2) {
                    result = false;
                    break;
                }
                for (Node<T> child : el.leaves()) {
                    queue.offer(child);
                }
            }
        }
        return result;
    }
}
