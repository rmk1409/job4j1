package ru.job4j.chess;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 03.09.2019
 */
public class LogicTest {
    private Logic logic;

    @Before
    public void init() {
        logic = new Logic();
        logic.add(new BishopBlack(Cell.C8));

    }

    @Test
    public void moveTrue() {
        assertTrue(logic.move(Cell.C8, Cell.A6));
    }

    @Test(expected = IllegalStateException.class)
    public void moveNotDiagonal() {
        logic.move(Cell.C8, Cell.A7);
    }

    @Test
    public void moveObstacle() {
        logic.add(new BishopBlack(Cell.A6));
        assertFalse(logic.move(Cell.C8, Cell.A6));
    }
}