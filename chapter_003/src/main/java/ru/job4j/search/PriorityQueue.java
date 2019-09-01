package ru.job4j.search;

import java.util.LinkedList;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        switch (task.getPriority()) {
            case 1:
                tasks.addFirst(task);
                break;
            case 5:
                tasks.addLast(task);
                break;
            default:
                tasks.add(tasks.size() >> 1, task);
                break;
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
