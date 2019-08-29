package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EndsWithTest {
    private EndsWith word = new EndsWith();

    @Test
    public void whenStartWithPrefixThenTrue() {
        boolean result = word.endsWith("Hello", "lo");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        boolean result = word.endsWith("Hello", "la");
        assertThat(result, is(false));
    }
}