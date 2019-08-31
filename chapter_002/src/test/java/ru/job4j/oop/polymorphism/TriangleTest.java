package ru.job4j.oop.polymorphism;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class TriangleTest {

    @Test
    public void draw() {
        assertThat(new Triangle().draw(), is(
                new StringBuilder()
                        .append("  *")
                        .append(" ***")
                        .append("*****")
                        .toString()
                )
        );
    }
}