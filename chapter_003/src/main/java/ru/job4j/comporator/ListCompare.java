package ru.job4j.comporator;

import java.util.Comparator;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int i = 0;
        while (result == 0 && i < left.length() && i < right.length()) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            i++;
        }
        if (result == 0) {
            if (i < left.length()) {
                result = 1;
            } else if (i < right.length()) {
                result = -1;
            }
        }
        return result;
    }
}