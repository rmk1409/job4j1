package ru.job4j.solid.isp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class AbstNode implements Composite {
    private int level;
    private String name;
    private List<Composite> children = new ArrayList<>();

    public AbstNode(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void execute() {
        IntStream.range(0, level).forEach(i -> System.out.print("\t"));
        System.out.println(this.name);
        children.forEach(Composite::execute);
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

    public void setName(String name) {
        this.name = name;
    }
}
