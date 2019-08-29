package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        String[] actual = new ArrayDuplicate().remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"});
        String[] expected = {"Привет", "Мир", "Супер"};

        assertThat(actual, is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate2() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        String[] actual = new ArrayDuplicate().remove(new String[]{"Привет", "Мир", "Привет", "Привет", "Привет", "Супер", "Мир"});
        String[] expected = {"Привет", "Мир", "Супер"};

        assertThat(actual, is(expected));
    }
}