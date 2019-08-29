package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckPrimeNumberTest {
    private CheckPrimeNumber prime = new CheckPrimeNumber();

    @Test
    public void when5() {
        boolean rsl = prime.check(5);
        assertThat(rsl, is(true));
    }

    @Test
    public void when4() {
        boolean rsl = prime.check(4);
        assertThat(rsl, is(false));
    }
}