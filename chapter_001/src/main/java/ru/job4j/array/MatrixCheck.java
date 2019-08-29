package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result;
        boolean hasMiddle = data[0].length % 2 != 0;

        boolean sample;
        if (hasMiddle) {
            int middle = (int) Math.floor(data[0].length / 2);
            sample = data[middle][middle];

            result = this.check1stDiagonal(data, sample);

            if (result) {
                result = this.check2ndDiagonal(data, sample);
            }
        } else {
            sample = data[0][0];

            result = this.check1stDiagonal(data, sample);

            if (result) {
                sample = data[0][data.length - 1];
                result = this.check2ndDiagonal(data, sample);
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
