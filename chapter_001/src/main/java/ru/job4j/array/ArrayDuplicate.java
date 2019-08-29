package ru.job4j.array;

import static java.util.Arrays.*;

/**
 * Class has method, which can remove the duplicates from the array.
 * @author roman.pogorelov
 * @since 29.08.2019
 */
public class ArrayDuplicate {
    /**
     * Remove duplicates from array
     *
     * @param array
     * @return array without duplicates
     */
    public String[] remove(String[] array) {
        int duplicates = 0;
        for (int i = 0; i < array.length - duplicates; i++) {
            String current = array[i];
            for (int j = i + 1; j < array.length - duplicates; j++) {
                if (current.equals(array[j])) {
                    String tmp = array[j];
                    array[j] = array[array.length - 1 - duplicates];
                    array[array.length - 1] = tmp;
                    j--;
                    duplicates++;
                }
            }
        }
        return copyOf(array, array.length - duplicates);
    }
}
