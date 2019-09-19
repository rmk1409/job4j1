package ru.job4j.solid.oc;

import ru.job4j.solid.sr.InteractCalc;

import java.util.Objects;
import java.util.Scanner;

/**
 * На базе задания из занятия SRP расширить калькулятор. Сделать инженерный калькулятор. Добавить вычисления например тригонометрии.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class EngineerCalc extends InteractCalc {
    public EngineerCalc(String operation) {
        super(operation);
    }

    public EngineerCalc(String operation, Scanner scanner) {
        super(operation, scanner);
    }

    @Override
    public void calc() {
        if (!(Objects.equals("Sin", this.operation) || Objects.equals("Cos", this.operation) || Objects.equals("Tan", this.operation))) {
            super.calc();
        } else {
            System.out.print("Input a number: ");
            var number = this.scanner.nextInt();
            switch (this.operation) {
                case "Sin":
                    System.out.println(String.format("%s(%d) = %.2f", operation, number, Math.sin(Math.toRadians(number))));
                    break;
                case "Cos":
                    System.out.println(String.format("%s(%d) = %.2f", operation, number, Math.cos(Math.toRadians(number))));
                    break;
                case "Tan":
                    System.out.println(String.format("%s(%d) = %.2f", operation, number, Math.tan(Math.toRadians(number))));
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
