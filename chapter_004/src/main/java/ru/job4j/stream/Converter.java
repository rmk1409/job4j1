package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class Converter {
    public List<Integer> convert(Integer[][] array) {
        List<List<Integer>> list = new ArrayList<>();
        for (Integer[] integers : array) {
            list.add(Arrays.asList(integers));
        }
        return list.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Integer[][] in = {
                {1, 2},
                {3, 4, 5}
        };
        System.out.println(Arrays.asList(in));
    }
}
