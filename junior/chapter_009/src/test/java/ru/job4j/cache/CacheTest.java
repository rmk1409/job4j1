package ru.job4j.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class CacheTest {
    private Cache cache;

    @Before
    public void init() {
        this.cache = new Cache();
    }

    @Test
    public void testEmptyCache() {
        assertThat(this.cache.getInnerStorage().size(), is(0));
    }

    @Test
    public void testCache() {
        String addresses = this.cache.getValue("Address.txt");
        String expected = new StringJoiner(System.lineSeparator())
                .add("address1")
                .add("address2")
                .add("address3")
                .toString();
        assertThat(addresses, is(expected));
        assertThat(cache.getInnerStorage().size(), is(1));
        assertThat(cache.getInnerStorage().get("Address.txt").get(), is(expected));
    }

}