package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {
    private Board board = new Board();

    @Test
    public void when3x3() {
        String rsl = board.paint(3, 3);
        String ln = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X%s X %sX X%s", ln, ln, ln)
                )
        );
    }

    @Test
    public void when5x4() {
        String rsl = board.paint(5, 4);
        String ln = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X X%1$s X X %1$sX X X%1$s X X %1$s", ln)
                )
        );
    }
}