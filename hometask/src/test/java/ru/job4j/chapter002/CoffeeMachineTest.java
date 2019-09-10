package ru.job4j.chapter002;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Pogorelov Roman
 * @since 04.09.2019
 */
public class CoffeeMachineTest {
    private CoffeeMachine machine = new CoffeeMachine();

    @Test
    public void changes() {
        int[] expected = {10, 5};
        assertThat(expected, is(this.machine.changes(50, 35)));
    }

    @Test
    public void changes2() {
        int[] expected = {10, 2};
        assertThat(expected, is(this.machine.changes(50, 38)));
    }

    @Test
    public void changes3() {
        int[] expected = {};
        assertThat(expected, is(this.machine.changes(50, 50)));
    }

    @Test
    public void changes4() {
        int[] expected = {10, 10, 2, 1};
        assertThat(expected, is(this.machine.changes(50, 27)));
    }

    @Test
    public void changesOtherCoinTypes() {
        CoffeeMachine machine = new CoffeeMachine(new Integer[]{1, 10});
        int[] expected = {10, 10, 1, 1, 1};
        assertThat(expected, is(machine.changes(50, 27)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void changesException() {
        this.machine.changes(50, 51);
    }
}