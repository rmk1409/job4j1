package ru.job4j.solid.ls;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private boolean canReproduct;
    private boolean isVegetable;

    public Food(String name, Date expiryDate, Date createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public Food(String name, Date expiryDate, Date createDate, double price, double discount, boolean canReproduct, boolean isVegetable) {
        this(name, expiryDate, createDate, price, discount);
        this.canReproduct = canReproduct;
        this.isVegetable = isVegetable;
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

    public double getSpoiled() {
        var result = 0.0;
        var period = TimeUnit.DAYS.convert(this.expiryDate.getTime() - this.createDate.getTime(), TimeUnit.MILLISECONDS);
        long passed = TimeUnit.DAYS.convert(System.currentTimeMillis() - this.createDate.getTime(), TimeUnit.MILLISECONDS);
        result = passed == 0 ? 0 : passed / 1.0 / period;
        return result;
    }

    public boolean isCanReproduct() {
        return canReproduct;
    }

    public void setCanReproduct(boolean canReproduct) {
        this.canReproduct = canReproduct;
    }

    public boolean isVegetable() {
        return isVegetable;
    }

    public void setVegetable(boolean vegetable) {
        isVegetable = vegetable;
    }
}
