package ru.job4j.solid.sr;

import java.util.List;

/**
 * Используя класс Calculator.
 * 1. Сделать класс InteractCalc.
 * 2. В классе должен быть пользовательский ввод.
 * 3. Повторный выбор операции и переиспользование предыдущего вычисления.
 * 4. Проект должен следовать SRP.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class InteractCalc {
    protected ConsoleInput input;
    protected List<Act> acts;

    public InteractCalc(ConsoleInput input, List<Act> acts) {
        this.input = input;
        this.acts = acts;
    }

    public void start() {
        if (this.acts.size() > 0) {
            int choice;
            do {
                System.out.println("Choose: ");
                System.out.println("-1. Exit.");
                for (int i = 0; i < acts.size(); i++) {
                    System.out.println(String.format("%d. %s.", i, this.acts.get(i).info()));
                }
                choice = this.input.askNextInput("");
                if (choice != -1) {
                    this.acts.get(choice).act(this.input);
                }
                System.out.println("-----------------------------");
            } while (choice != -1);
        }
    }

    public static void main(String[] args) {
        new InteractCalc(
                new ConsoleInput(),
                List.of(new AddAct(), new DivAct())
        ).start();
    }
}
