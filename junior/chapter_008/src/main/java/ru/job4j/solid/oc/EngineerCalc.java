package ru.job4j.solid.oc;

import ru.job4j.solid.sr.InteractCalc;

import java.util.Scanner;

/**
 * На базе задания из занятия SRP расширить калькулятор. Сделать инженерный калькулятор. Добавить вычисления например тригонометрии.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class EngineerCalc extends InteractCalc {
    public EngineerCalc() {
    }

    public EngineerCalc(Scanner scanner) {
        super(scanner);
    }

    {
        this.menu.add("5. Sin");
        this.menu.add("6. Cos");
        this.menu.add("7. Tan");
    }

    {
        this.operations.put(5, (x, y) -> System.out.println(String.format("sin(%d) = %.2f", x, Math.sin(Math.toRadians(x)))));
        this.operations.put(6, (x, y) -> System.out.println(String.format("cos(%d) = %.2f", x, Math.cos(Math.toRadians(x)))));
        this.operations.put(7, (x, y) -> System.out.println(String.format("tan(%d) = %.2f", x, Math.tan(Math.toRadians(x)))));
    }
}
