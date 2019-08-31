package ru.job4j.pattern.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class EagerInitTrackerTest {

    @Test
    public void getInstance() {
        EagerInitTracker one = EagerInitTracker.getInstance();
        assertThat(EagerInitTracker.getInstance(), is(one));
        assertThat(EagerInitTracker.getInstance(), is(one));
        assertThat(EagerInitTracker.getInstance(), is(one));
        assertThat(EagerInitTracker.getInstance(), is(one));
        assertThat(EagerInitTracker.getInstance(), is(one));
    }
}