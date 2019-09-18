package ru.job4j.solid.ls;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class ControlQualityTest {
    private ControlQuality controlQuality;

    @Before
    public void init() {
        this.controlQuality = new ControlQuality(List.of(new Warehouse(), new Shop(), new Trash()));
    }

    @Test
    public void checkWarehouse() {
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.MONTH, 3);
        Food aNew = new Food("new", expiry.getTime(), Calendar.getInstance().getTime(), 100.0, 0);
        this.controlQuality.sendProduct(aNew);
        assertThat(this.controlQuality.getStorages().get(0).getStorage().size(), is(1));
    }

    @Test
    public void checkShop() {
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.MONTH, 1);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -1);
        Food half = new Food("half", expiry.getTime(), created.getTime(), 100.0, 0);
        this.controlQuality.sendProduct(half);
        assertThat(this.controlQuality.getStorages().get(1).getStorage().size(), is(1));
        assertThat(half.getDiscount(), is(0.0));
    }

    @Test
    public void checkDiscountShop() {
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.MONTH, 1);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -3);
        Food discount = new Food("discount", expiry.getTime(), created.getTime(), 100.0, 0);
        this.controlQuality.sendProduct(discount);
        assertThat(this.controlQuality.getStorages().get(1).getStorage().size(), is(1));
        assertThat(discount.getDiscount(), is(50.0));
    }

    @Test
    public void checkTrash() {
        Calendar expiry = Calendar.getInstance();
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -3);
        Food trash = new Food("trash", expiry.getTime(), created.getTime(), 100.0, 0);
        this.controlQuality.sendProduct(trash);
        assertThat(this.controlQuality.getStorages().get(2).getStorage().size(), is(1));
    }
}