package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class AccountTest {

    @Test
    public void transferMoney() {
        Account src = new Account(10, "req");
        Account dest = new Account(0, "req2");
        src.transferMoney(dest, 8);
        assertThat(2.0, is(src.getValue()));
        assertThat(8.0, is(dest.getValue()));
    }
}