package ru.job4j.chapter_004;

import java.util.Arrays;

/**
 * Задан массив чисел.
 * <p>
 * 1. Нужно него отфильтровать, оставить только четные числа.
 * <p>
 * 2. Каждое число возвести в квадрат.
 * <p>
 * 3. И все элементы просуммировать.
 * <p>
 * Created by roman.pogorelov on 05.09.2019
 */
public class StreamForArray {
    public int work(int[] in) {
        return Arrays.stream(in)
                .filter(i -> i % 2 == 0)
                .map(i -> (int) Math.pow(i, 2))
                .reduce(0, Integer::sum);
    }
}
