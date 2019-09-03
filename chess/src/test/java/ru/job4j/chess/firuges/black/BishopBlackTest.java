package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class BishopBlackTest {

    @Test
    public void position() {
        assertThat(Cell.C8, is(new BishopBlack(Cell.C8).position()));
    }

    @Test
    public void copy() {
        assertThat(Cell.C2, is(new BishopBlack(Cell.C8).copy(Cell.C2).position()));
    }

    @Test
    public void way() {
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] actual = new BishopBlack(Cell.C1).way(Cell.C1, Cell.G5);
        assertThat(expected, is(actual));
    }

    @Test
    public void isDiagonal() {
        boolean actual = new BishopBlack(Cell.C8).isDiagonal(Cell.D4, Cell.H8);
        assertTrue(actual);
    }

    @Test
    public void isDiagonalFalse() {
        boolean actual = new BishopBlack(Cell.C8).isDiagonal(Cell.D4, Cell.H7);
        assertFalse(actual);
    }
}