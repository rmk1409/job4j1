package ru.job4j.chapter006;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 10.09.2019
 */
public class ScriptBuilderTest {
    // 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - []
    Map<Integer, List<Integer>> map = Map.of(1, List.of(2, 3), 2, List.of(4), 3, List.of(4, 5), 5, List.of(0), 4, List.of(), 0, List.of());

    @Test
    public void recursiveLoad() {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        List<Integer> expected = List.of(4, 2, 0, 5, 3);
        assertThat(scriptBuilder.loadRecursion(map, 1), is(expected));
    }

    @Test
    public void notRecursiveLoad() {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        List<Integer> expected = List.of(0, 5, 4, 3, 2);
        assertThat(scriptBuilder.loadNoRecursion(map, 1), is(expected));
    }
}