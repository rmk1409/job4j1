package ru.job4j.bank;

import java.util.*;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class Bank {
    // TODO find where we can use "List.indexOf"

    /**
     * Users
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Method adds new user.
     *
     * @param user new entity
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method deletes the user.
     *
     * @param user entity for deleting
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Method adds the account to the user.
     *
     * @param passport is used to find the user
     * @param account  new account
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> entity : this.users.entrySet()) {
            if (passport.equals(entity.getKey().getPassword())) {
                entity.getValue().add(account);
                break;
            }
        }
    }

    /**
     * Method deletes the account from the user.
     *
     * @param passport is used to find the user
     * @param account  is used for removing
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> entity : this.users.entrySet()) {
            if (passport.equals(entity.getKey().getPassword())) {
                entity.getValue().remove(account);
                break;
            }
        }
    }

    /**
     * Method returns list of all user accounts.
     *
     * @param passport is used to find the user
     * @return list of all user accounts
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> entity : this.users.entrySet()) {
            if (passport.equals(entity.getKey().getPassword())) {
                result.addAll(entity.getValue());
                break;
            }
        }
        return result;
    }

    /**
     * Method transfers money from one Account to another.
     *
     * @param srcPassport  is used to find the src User
     * @param srcRequisite is used to find the src Account
     * @param destPassport is used to find the dest User
     * @param dstRequisite is used to find the dest Account
     * @param amount       money for the transfer
     * @return whether it is successful or not
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account src = null;
        Account dest = null;
        for (Account account : this.getUserAccounts(srcPassport)) {
            if (srcRequisite.equals(account.getRequisites())) {
                src = account;
            }
        }
        for (Account account : this.getUserAccounts(destPassport)) {
            if (dstRequisite.equals(account.getRequisites())) {
                dest = account;
            }
        }
        if (src != null && dest != null && amount <= src.getValue()) {
            src.setValue(src.getValue() - amount);
            dest.setValue(dest.getValue() + amount);
            result = true;
        }
        return result;
    }

    public Map<User, List<Account>> getUsers() {
        return users;
    }

    public void setUsers(Map<User, List<Account>> users) {
        this.users = users;
    }
}
