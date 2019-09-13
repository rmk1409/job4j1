package ru.job4j.jdbc.xml;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 13.09.2019
 */
public class StoreSQLTest {
    private StoreSQL sqlite = new StoreSQL(new Config());

    @Test
    public void generate() {
        int testData = 10;
        sqlite.generate(testData);
        assertThat(sqlite.load().size(), is(testData));
    }

    @Test
    public void load() {
        List<Entry> expected = List.of(new Entry(0), new Entry(1), new Entry(2));
        sqlite.generate(expected.size());
        assertThat(sqlite.load(), is(expected));
    }
}