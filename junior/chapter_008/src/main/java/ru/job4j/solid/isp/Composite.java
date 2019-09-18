package ru.job4j.solid.isp;

import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public interface Composite {
    void execute();
    void add(Composite node);
    List<Composite> getChildren();
    void invoke();
}
