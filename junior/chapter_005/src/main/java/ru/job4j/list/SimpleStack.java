package ru.job4j.list;

/**
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> container = new SimpleLinkedList<>();

    /**
     * должен возвращать значение и удалять его из коллекции.
     *
     * @return верхний элемент
     */
    public T poll() {
        return this.container.delete();
    }

    /**
     * помещает значение в коллекцию.
     *
     * @param value для помещения в коллекцию
     */
    public void push(T value) {
        this.container.add(value);
    }

    public boolean empty(){
        return this.container.getSize() == 0;
    }
}
