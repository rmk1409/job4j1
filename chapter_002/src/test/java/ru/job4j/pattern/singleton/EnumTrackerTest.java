package ru.job4j.pattern.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class EnumTrackerTest {
    @Test
    public void getInstance(){
        EnumTracker one = EnumTracker.INSTANCE;
        assertThat(EnumTracker.INSTANCE, is(one));
        assertThat(EnumTracker.INSTANCE, is(one));
        assertThat(EnumTracker.INSTANCE, is(one));
        assertThat(EnumTracker.INSTANCE, is(one));
        assertThat(EnumTracker.INSTANCE, is(one));
    }
}