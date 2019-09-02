package ru.job4j.sorting;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class SortUserTest {

    @Test
    public void sort() {
        User user1 = new User(10, "1");
        User user2 = new User(5, "2");
        User user3 = new User(11, "3");
        Set<User> expected = Set.of(user1, user2, user3);
        List<User> in = List.of(user1, user2, user3);
        Set<User> actual = new SortUser().sort(in);
        assertThat(expected, is(actual));
    }

    @Test
    public void sortNameLength() {
        User user1 = new User(25, "Сергей");
        User user2 = new User(30, "Иван");
        User user3 = new User(20, "Сергей");
        User user4 = new User(25, "Иван");
        List<User> expected = List.of(user2, user4, user1, user3);
        assertThat(expected, is(new SortUser().sortNameLength(Arrays.asList(user1, user2, user3, user4))));
    }

    @Test
    public void sortByAllFields() {
        User user1 = new User(25, "Сергей");
        User user2 = new User(30, "Иван");
        User user3 = new User(20, "Сергей");
        User user4 = new User(25, "Иван");
        List<User> expected = List.of(user4, user2, user3, user1);
        assertThat(expected, is(new SortUser().sortByAllFields(Arrays.asList(user1, user2, user3, user4))));
    }
}