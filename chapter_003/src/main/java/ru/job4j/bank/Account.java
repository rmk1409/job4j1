package ru.job4j.bank;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class Account {
    private double value;
    private String requisites;

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    /**
     * Method transfers amount from this to dest Account.
     *
     * @param dest   destination Account
     * @param amount quantity
     * @return whether it is successful or not
     */
    public boolean transferMoney(Account dest, double amount) {
        boolean result = false;
        if (dest != null && amount <= this.getValue()) {
            this.setValue(this.getValue() - amount);
            dest.setValue(dest.getValue() + amount);
            result = true;
        }
        return result;
    }
}
