package ru.job4j.oop.inheritance;

/**
 * Child
 * Created by roman.pogorelov on 30.08.2019
 */
public class Programmer extends Engineer {
    private int bugs;

    public int getBugs() {
        return bugs;
    }

    public Program refactor(Program program) {return new Program();}
}
