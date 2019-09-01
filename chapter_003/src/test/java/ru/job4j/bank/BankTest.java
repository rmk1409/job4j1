package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class BankTest {
    private Bank bank;
    private User vasya;

    @Before
    public void init() {
        bank = new Bank();
        vasya = new User("Vasya", "pass");
        bank.addUser(vasya);
    }

    @Test
    public void addUser() {
        int expected = 1;
        assertThat(expected, is(bank.getUsers().size()));
    }

    @Test
    public void deleteUser() {
        bank.deleteUser(vasya);
        int expected = 0;
        assertThat(expected, is(bank.getUsers().size()));
    }

    @Test
    public void addAccountToUser() {
        Account account = new Account(100, "requis");
        bank.addAccountToUser(this.vasya.getPassword(), account);
        int expected = 1;
        assertThat(expected, is(bank.getUserAccounts(this.vasya.getPassword()).size()));
    }

    @Test
    public void deleteAccountFromUser() {
        Account account = new Account(100, "requis");
        bank.addAccountToUser(this.vasya.getPassword(), account);
        bank.deleteAccountFromUser(this.vasya.getPassword(), account);
        int expected = 0;
        assertThat(expected, is(bank.getUserAccounts(this.vasya.getPassword()).size()));
    }

    @Test
    public void getUserAccounts() {
        int expected = 0;
        assertThat(expected, is(bank.getUserAccounts(this.vasya.getPassword()).size()));
    }

    @Test
    public void transferMoneyToSelf() {
        Account account = new Account(100, "requis");
        Account secret = new Account(300, "secret-requis");
        bank.addAccountToUser(this.vasya.getPassword(), account);
        bank.addAccountToUser(this.vasya.getPassword(), secret);
        boolean result = bank.transferMoney(this.vasya.getPassword(), account.getRequisites(), this.vasya.getPassword(), secret.getRequisites(), 70);
        assertThat(true, is(result));
        assertThat(30.0, is(account.getValue()));
        assertThat(370.0, is(secret.getValue()));
    }

    @Test
    public void transferMoneyToAnotherPerson() {
        Account account = new Account(100, "requis");
        bank.addAccountToUser(this.vasya.getPassword(), account);
        User brother = new User("Milka", "pass2");
        bank.addUser(brother);
        Account secret = new Account(300, "secret-requis");
        bank.addAccountToUser(brother.getPassword(), secret);
        boolean result = bank.transferMoney(this.vasya.getPassword(), account.getRequisites(), brother.getPassword(), secret.getRequisites(), 70);
        assertThat(true, is(result));
        assertThat(30.0, is(account.getValue()));
        assertThat(370.0, is(secret.getValue()));
    }

    @Test
    public void transferMoneyWhenNotEnoughMoney() {
        Account account = new Account(100, "requis");
        bank.addAccountToUser(this.vasya.getPassword(), account);
        User brother = new User("Milka", "pass2");
        bank.addUser(brother);
        Account secret = new Account(300, "secret-requis");
        bank.addAccountToUser(brother.getPassword(), secret);
        boolean result = bank.transferMoney(this.vasya.getPassword(), account.getRequisites(), brother.getPassword(), secret.getRequisites(), 101);
        assertThat(false, is(result));
        assertThat(100.0, is(account.getValue()));
        assertThat(300.0, is(secret.getValue()));
    }
}