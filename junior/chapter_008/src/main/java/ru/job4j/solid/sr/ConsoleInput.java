package ru.job4j.solid.sr;

import java.util.Scanner;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public int askNextInput(String msg){
        System.out.print(msg);
        return this.scanner.nextInt();
    }
}
