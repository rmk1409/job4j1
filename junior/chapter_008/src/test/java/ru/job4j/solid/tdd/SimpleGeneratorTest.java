package ru.job4j.solid.tdd;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class SimpleGeneratorTest {

    @Test
    public void simpleGenerate() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr", "subject", "you");
        String actual = new SimpleGenerator().generate(template, values);
        String expected = "I am Petr, Who are you?";
        assertThat(actual, is(expected));
    }

    @Test
    public void oneValueToThreePlaces() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> values = Map.of("sos", "Aaa");
        String actual = new SimpleGenerator().generate(template, values);
        String expected = "Help, Aaa, Aaa, Aaa";
        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noKeys() {
        new SimpleGenerator().generate("Hi, ${name}", Map.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void extraKeys() {
        new SimpleGenerator().generate("Hi, ${name}", Map.of("name", "Petr", "name2", "Maria"));
    }
}