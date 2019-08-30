package ru.job4j.tracker;

import java.util.Scanner;

/**
 * In/output system.
 * Created by roman.pogorelov on 30.08.2019
 */
public class ConsoleInput implements Input{
    private Scanner in = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return in.nextLine();
    }
}
