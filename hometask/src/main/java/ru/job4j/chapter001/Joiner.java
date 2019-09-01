package ru.job4j.chapter001;

/**
 * Даны два отсортированных массива.
 * <p>
 * Вам нужно написать метод, который берет элементы этих массивов и добавляет в третий массив.
 * <p>
 * int[] merge(int[] left, int[] right)
 * <p>
 * Третий массив должен получится тоже отсортированный.
 * <p>
 * Например:
 * <p>
 * Массив 1                    int[] a = new int[] {1, 3};
 * <p>
 * Массив 2                    int[] b = new int[] {2, 4};
 * <p>
 * <p>
 * <p>
 * Массив результат, который появится после слияния.
 * <p>
 * int[] result = new int[] {1, 2, 3, 4};
 * <p>
 * В задаче нельзя использовать сортировку.
 * Created by roman.pogorelov on 31.08.2019
 */
public class Joiner {
    /**
     * Метод принимает 2 массива, объединяет их и вовзращает объединённый отсортированный.
     *
     * @param first  1й массив
     * @param second 2й массив
     * @return объединённый отсортированный массив
     */
    public int[] join(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;
        while (j < first.length && k < second.length) {
            result[i++] = first[j] < second[k] ? first[j++] : second[k++];
        }
        while (j < first.length) {
            result[i++] = first[j++];
        }
        while (k < second.length) {
            result[i++] = second[k++];
        }
        return result;
    }
}
