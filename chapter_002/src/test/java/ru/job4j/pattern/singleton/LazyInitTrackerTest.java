package ru.job4j.pattern.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class LazyInitTrackerTest {

    @Test
    public void getInstance() {
        LazyInitTracker one = LazyInitTracker.getInstance();
        assertThat(LazyInitTracker.getInstance(), is(one));
        assertThat(LazyInitTracker.getInstance(), is(one));
        assertThat(LazyInitTracker.getInstance(), is(one));
        assertThat(LazyInitTracker.getInstance(), is(one));
        assertThat(LazyInitTracker.getInstance(), is(one));
    }
}