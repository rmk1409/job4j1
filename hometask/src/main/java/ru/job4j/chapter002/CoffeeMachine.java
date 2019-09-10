package ru.job4j.chapter002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private Integer[] types;

    public CoffeeMachine() {
        types = new Integer[]{10, 5, 2, 1};
    }

    public CoffeeMachine(Integer[] types) {
        this.types = types;
    }

    public int[] changes(int value, int price) {
        this.checkValideInputData(value, price);
        List<Integer> list = new ArrayList<>();
        int changes = value - price;
        if (changes > 0) {
            Arrays.sort(types, Collections.reverseOrder());
            for (Integer type : types) {
                int quantity = changes / type;
                changes %= type;
                IntStream.range(0, quantity)
                        .forEach(i -> list.add(type));
            }
        }
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private void checkValideInputData(int value, int price) {
        if (price < 0 || value < 0 || value < price) {
            throw new IllegalArgumentException("Please enter appropriate numbers.");
        }
    }
}
