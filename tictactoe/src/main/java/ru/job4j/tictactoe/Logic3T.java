package ru.job4j.tictactoe;

import java.nio.file.DirectoryStream;
import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.checkWinners("x");
    }

    public boolean isWinnerO() {
        return this.checkWinners("o");
    }

    private boolean checkWinners(String who) {
        int[][] checker = {
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 2, 1, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 1},
                {2, 0, 0, 1},
                {0, 0, 1, 1},
                {2, 0, -1, 1}
        };
        final Predicate<Figure3T> mark = "x".equals(who) ? Figure3T::hasMarkX : Figure3T::hasMarkO;
        return Arrays.stream(checker)
                .anyMatch(row -> this.fillBy(mark, row[0], row[1], row[2], row[3]));
    }

    public boolean hasGap() {
        return Arrays.stream(table)
                .flatMap(Arrays::stream)
                .anyMatch(el -> !el.hasMarkO() && !el.hasMarkX());
    }
}
