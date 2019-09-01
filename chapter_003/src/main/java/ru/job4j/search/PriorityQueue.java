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
        int priority = task.getPriority();
        if (tasks.isEmpty() || priority < tasks.get(0).getPriority()) {
            tasks.addFirst(task);
        } else {
            int index = 0;
            for (Task item: tasks) {
                if (priority > item.getPriority()) {
                    index++;
                } else {
                    break;
                }
            }
            tasks.add(index, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
