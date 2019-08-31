package ru.job4j.tracker;

import java.util.List;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, List<Integer> range) {
        int result = -1;
        boolean flag = true;
        do {
            try {
                result = super.ask(question, range);
                flag = false;
            } catch (MenuOutException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Необходимо выбрать значение из диапазона меню и запросить повторно ввод.");
            } catch (NumberFormatException exception) {
                System.out.println("Необходимо ввести корректное значение и запросить повторно ввод.");
            }
        } while (flag);
        return result;
    }
}
