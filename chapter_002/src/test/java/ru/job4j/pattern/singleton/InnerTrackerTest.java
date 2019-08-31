package ru.job4j.pattern.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class InnerTrackerTest {

    @Test
    public void getInstance() {
        InnerTracker one = InnerTracker.getInstance();
        assertThat(InnerTracker.getInstance(), is(one));
        assertThat(InnerTracker.getInstance(), is(one));
        assertThat(InnerTracker.getInstance(), is(one));
        assertThat(InnerTracker.getInstance(), is(one));
        assertThat(InnerTracker.getInstance(), is(one));
    }
}