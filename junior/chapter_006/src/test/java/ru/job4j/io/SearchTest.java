package ru.job4j.io;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 08.09.2019
 */
public class SearchTest {

    @Test
    @Ignore // Test is locally working, but it breaks on CI server, that's why @Ignore
    public void files() {
        List<File> actual = new Search().files(System.getProperty("java.io.tmpdir") + "/java/parent", List.of("txt"));
        List<File> expected = List.of(new File(System.getProperty("java.io.tmpdir") + "/java/parent/child2/2.txt"), new File(System.getProperty("java.io.tmpdir") + "/java/parent/child2/3.txt"));
        assertThat(actual, is(expected));
    }
}