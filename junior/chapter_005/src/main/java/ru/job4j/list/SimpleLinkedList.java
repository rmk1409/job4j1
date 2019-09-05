package ru.job4j.list;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleLinkedList<E> {
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Проверка на зацикленность
     *
     * @param first
     * @return true or false
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node turtle = first;
        if (first != null) {
            Node rabbit = first.next;
            while (true) {
                if (rabbit == turtle) {
                    result = true;
                    break;
                }
                if (turtle == null || rabbit == null || rabbit.next == null) {
                    break;
                }
                turtle = turtle.next;
                rabbit = rabbit.next.next;
            }
        }
        return result;
    }

    /**
     * Метод удаления первого элемент в списке.
     */
    public E delete() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        E result = this.first.data;
        this.first = first.next;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    public static class Node<E> {

        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
