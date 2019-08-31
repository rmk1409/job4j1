package ru.job4j.tracker;

import java.util.List;

/**
 * Interface for in/output systems.
 * Created by roman.pogorelov on 30.08.2019
 */
public interface Input {
    String ask(String question);

    int ask(String question, List<Integer> range);
}
