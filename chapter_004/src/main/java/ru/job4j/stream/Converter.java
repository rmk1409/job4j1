package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class Converter {
    public List<Integer> convert(Integer[][] array) {
        return Arrays.stream(array)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
