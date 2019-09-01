package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int size = list.size();
        int cells = (int) Math.ceil(size / 1.0 / rows);
        int[][] array = new int[rows][cells];
        for (int i = 0, k = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length && k < size; j++) {
                array[i][j] = list.get(k++);
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.forEach(array -> {
            for (int element : array) {
                result.add(element);
            }
        });
        return result;
    }
}
