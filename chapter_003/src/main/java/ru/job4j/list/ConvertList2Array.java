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
        int i = 0, j = 0, k = 0;
        for (int[] outer : result) {
            for (int inner : outer) {
                result[i][j++] = list.get(k++);
                if (k == size) {
                    break;
                }
            }
            i++;
            j = 0;
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
