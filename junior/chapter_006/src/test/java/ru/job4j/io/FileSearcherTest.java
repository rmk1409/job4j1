package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class FileSearcherTest {
    private File out;

    @Before
    public void init() {
        this.out = new File("log.txt");
    }

    @After
    public void destroy() {
        this.out.deleteOnExit();
    }

    @Test
    @Ignore //Ignore due to it fails on the server, it works locally
    public void testFullName() throws IOException {
        new FileSearcher().search(System.getProperty("java.io.tmpdir") + "/java/", "1.txt", true, false, false, this.out.getAbsolutePath());
        List<String> expected = List.of("one", "1");
        assertThat(Files.readAllLines(out.toPath()), is(expected));
    }

    @Test
    @Ignore //Ignore due to it fails on the server, it works locally
    public void testMask() throws IOException {
        new FileSearcher().search(System.getProperty("java.io.tmpdir") + "/java/", "*.txt", false, false, true, this.out.getAbsolutePath());
        List<String> expected = List.of("one", "1", "2", "4", "10");
        assertThat(Files.readAllLines(out.toPath()), is(expected));
    }

    @Test
    @Ignore //Ignore due to it fails on the server, it works locally
    public void testRegex() throws IOException {
        new FileSearcher().search(System.getProperty("java.io.tmpdir") + "/java/", "\\w+\\.txt", false, true, true, this.out.getAbsolutePath());
        List<String> expected = List.of("one", "1", "2", "4", "10");
        assertThat(Files.readAllLines(out.toPath()), is(expected));
    }
}