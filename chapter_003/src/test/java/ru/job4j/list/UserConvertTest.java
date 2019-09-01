package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class UserConvertTest {

    @Test
    public void process() {
        User jon = new User(1, "Jon", "Mck");
        User albert = new User(2, "Albert", "Bryansk");
        List<User> users = new ArrayList<>(Arrays.asList(jon, albert));
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, jon);
        expected.put(2, albert);
        assertThat(expected, is(new UserConvert().process(users)));
    }
}