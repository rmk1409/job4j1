package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean tmp = false;
        if (data.length > 0) {
            tmp = data[0];
        }
        for (int i = 1; i < data.length; i++) {
            if (tmp != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
