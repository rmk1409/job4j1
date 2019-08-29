package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;

        for (int i = 1, length = data[0].length; i < length; i++) {
            boolean check1stDiagonal = data[i - 1][i - 1] == data[i][i];
            boolean check2ndDiagonal = data[length - 1][length - 1] == data[length - 1 - i][length - 1 - i];

            if (!check1stDiagonal && !check2ndDiagonal) {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean check1stDiagonal(boolean[][] data, boolean sample) {
        boolean result = true;

        for (int i = 0; i < data[0].length; i++) {
            if (sample != data[i][i]) {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean check2ndDiagonal(boolean[][] data, boolean sample) {
        boolean result = true;

        for (int i = 0, j = data[0].length - 1; i < data[0].length; i++, j--) {
            if (sample != data[i][j]) {
                result = false;
                break;
            }
        }

        return result;
    }
}
