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
        int[][] result = new int[rows][cells];
        int i = 0, j = 0;
        for (Integer number : list) {
            result[i][j++] = number;
            if (j == result.length) {
                j = 0;
                i++;
            }
        }
        return result;
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
