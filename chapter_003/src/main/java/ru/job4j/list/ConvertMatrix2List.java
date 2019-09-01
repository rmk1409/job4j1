package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] outer : array) {
            for (int inner : outer) {
                list.add(inner);
            }
        }
        return list;
    }
}
