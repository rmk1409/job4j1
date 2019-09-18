package ru.job4j.solid.ls;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Создать класс обработчик перераспределения продуктов в место использования.
 * <p>
 * 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.
 * 3.2 Если срок годности от 25% до 75% направить в Shop
 * 3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop
 * 3.4. Если срок годности вышел. Отправить продукт в мусорку.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class ControlQuality {
    private Storage trash = new Trash();
    private Storage warehouse = new Warehouse();
    private Storage shop = new Shop();
    private Strategy strategy;

    public void sendProduct(Food food) {
        Date createDate = food.getCreateDate();
        Date expiryDate = food.getExpiryDate();
        var diff = expiryDate.getTime() - createDate.getTime();
        var days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long passed = TimeUnit.DAYS.convert(System.currentTimeMillis() - createDate.getTime(), TimeUnit.MILLISECONDS);
        var percentage = passed == 0 ? 0 : passed / 1.0 / days;
        if (percentage < 0.25) {
            this.setStrategy(new WarehouseStrategy(this));
        } else if (percentage < 0.75) {
            this.setStrategy(new ShopStrategy(this));
        } else if (percentage < 1) {
            this.setStrategy(new ShopDiscountStrategy(this));
        } else {
            this.setStrategy(new TrashStrategy(this));
        }
        this.strategy.execute(food);
    }

    public Storage getTrash() {
        return trash;
    }

    public void setTrash(Storage trash) {
        this.trash = trash;
    }

    public Storage getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Storage warehouse) {
        this.warehouse = warehouse;
    }

    public Storage getShop() {
        return shop;
    }

    public void setShop(Storage shop) {
        this.shop = shop;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
