package ru.job4j.list;

/**
 * Описание Queue - очередь. Описывается FIFO - first input first output.
 * <p>
 * То есть, первый зашел и первый вышел. Например.
 * <p>
 * push(1);
 * push(2);
 * push(3);
 * <p>
 * poll() - 1
 * poll() - 2
 * poll() - 3
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleQueue<T> {
    private SimpleStack<T> first = new SimpleStack<>();
    private SimpleStack<T> second = new SimpleStack<>();

    public T poll() {
        if (this.second.empty()) {
            while (!this.first.empty()) {
                this.second.push(this.first.poll());
            }
        }
        return this.second.poll();
    }

    public void push(T value) {
        this.first.push(value);
    }
}
