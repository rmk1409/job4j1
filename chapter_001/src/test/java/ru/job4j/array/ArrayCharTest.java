package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayCharTest {
    private ArrayChar word = new ArrayChar();
    @Test
    public void whenStartWithPrefixThenTrue() {
        boolean result = word.startsWith("Hello", "He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        boolean result = word.startsWith("Hello", "Hi");
        assertThat(result, is(false));
    }
}