package ru.job4j.solid.ls;

import java.util.Date;

/**
 * Создать класс Food сполями. Name, expaireDate, createDate, price, disscount. На основе класса Food создать другие продукты.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class Food {
    private String name;
    private Date expiryDate;
    private Date createDate;
    private double price;
    private double discount;

    public Food(String name, Date expiryDate, Date createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
