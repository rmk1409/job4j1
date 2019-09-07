package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 07.09.2019
 */
public class ConfigTest {
    private Config config;

    @Before
    public void init() {
        this.config = new Config("..\\..\\" + "app.properties");
        this.config.load();
    }

    @Test
    public void load() {
        Map<String, String> expected = Map.of("k1", "v1", "k2.uoeu.ueo", "v2", "k3", "v3://321.34.34:ethu/eouth", "k4", "v4");
        assertThat(config.getValues(), is(expected));
    }

    @Test
    public void value() {
        assertThat(config.value("k1"), is("v1"));
        assertThat(config.value("k2.uoeu.ueo"), is("v2"));
        assertThat(config.value("k3"), is("v3://321.34.34:ethu/eouth"));
        assertThat(config.value("k4"), is("v4"));
    }
}