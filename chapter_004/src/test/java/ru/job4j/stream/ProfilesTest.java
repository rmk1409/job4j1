package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class ProfilesTest {

    @Test
    public void collect() {
        Address a1 = new Address("Msc", "zMain", 1, 1);
        Profile p = new Profile(a1);
        Address a2 = new Address("Piter", "Nevsk", 10, 10);
        Profile p2 = new Profile(a2);
        Address a3 = new Address("Piter", "Nevsk", 10, 10);
        Profile p3 = new Profile(a3);
        List<Address> expected = List.of(a2, a1);
        assertThat(expected, is(new Profiles().collect(List.of(p, p2, p3))));
    }
}