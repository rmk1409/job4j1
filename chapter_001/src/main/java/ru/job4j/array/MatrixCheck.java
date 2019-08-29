package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1, length = data.length; i < length; i++) {
            if (data[0][0] != data[i][i] || data[0][length - 1] != data[i][length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
