package ru.job4j.solid.isp;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class Root implements Composite, Comparable {
    private int level;
    private String name;
    private List<Composite> children = new ArrayList<>();

    public Root(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void execute() {
        Queue<Composite> queue = new PriorityQueue<>();
        queue.offer(this);
        while (queue.size() > 0) {
            Composite current = queue.poll();
            IntStream.range(0, current.getLevel()).forEach(i -> System.out.print("\t"));
            System.out.println(current.getName());
            current.getChildren().forEach(queue::offer);
        }
    }

    @Override
    public void add(Composite node) {
        this.children.add(node);
    }

    @Override
    public List<Composite> getChildren() {
        return this.children;
    }

    @Override
    public void invoke() {
        System.out.println(String.format("You choose %s.", this.name));
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(Object o) {
        return ((Composite) o).getLevel() - this.level;
    }
}
