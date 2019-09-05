package ru.job4j.chapter002;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Кофемашина. [#34741]
 * <p>
 * Задание такое.
 * Надо реализовать метод выдачи сдачи для автомата.
 * int[] changes(int value, int price)
 * value = купюра. 50 100 и тд.
 * price = цена кофе
 * в автомате монеты наминалом 1 2 5 10
 * Пример. Мы засунули 50 рублей выбрали кофе за 35. Сдачу автомат должен дать 15 рублей
 * Алгоритм должен вернуть наименьшее количество монет.
 * Метод вернет массив {10, 5} = 15 рублей
 * создать задачу залить в репозитори добавить ссылку
 *
 * @author Pogorelov Roman
 * @since 04.09.2019
 */
public class CoffeeMachine {
    public int[] changes(int value, int price) {
        List<Integer> list = new ArrayList<>();
        if (price >= 0 && value >= 0 && value >= price) {
            int changes = value - price;
            if (changes > 0) {
                int tens = changes / 10;
                changes %= 10;
                int fives = changes / 5;
                changes %= 5;
                int twos = changes / 2;
                changes %= 2;
                int ones = changes;
                IntStream.range(0, tens)
                        .forEach(i -> list.add(10));
                IntStream.range(0, fives)
                        .forEach(i -> list.add(5));
                IntStream.range(0, twos)
                        .forEach(i -> list.add(2));
                IntStream.range(0, ones)
                        .forEach(i -> list.add(1));
            }
        } else {
            throw new IllegalArgumentException("Please enter appropriate numbers.");
        }
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
