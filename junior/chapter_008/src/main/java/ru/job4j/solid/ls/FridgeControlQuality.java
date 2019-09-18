package ru.job4j.solid.ls;

import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class FridgeControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage fridge = new Trash();

    public FridgeControlQuality(ControlQuality controlQuality) {
        this.controlQuality = controlQuality;
        this.controlQuality.getStorages().add(fridge);
    }

    @Override
    public void sendProduct(Food food) {
        if (food.isVegetable() && 0.25 > food.getSpoiled()) {
            fridge.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }

    @Override
    public List<Storage> getStorages() {
        return this.controlQuality.getStorages();
    }
}
