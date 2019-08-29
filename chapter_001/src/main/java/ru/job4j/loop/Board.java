package ru.job4j.loop;

public class Board {
    public String paint(int width, int height) {
        StringBuilder result = new StringBuilder();
        String separator = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    result.append("X");
                } else {
                    result.append(" ");
                }
            }
            result.append(separator);
        }
        return result.toString();
    }
}
