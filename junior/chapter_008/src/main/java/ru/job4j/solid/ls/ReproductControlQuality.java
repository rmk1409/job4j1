package ru.job4j.solid.ls;

import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class ReproductControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage reproductionCenter = new Trash();

    public ReproductControlQuality(ControlQuality controlQuality) {
        this.controlQuality = controlQuality;
        this.controlQuality.getStorages().add(reproductionCenter);
    }

    @Override
    public void sendProduct(Food food) {
        if (food.isCanReproduct() && 1 < food.getSpoiled()) {
            this.reproductionCenter.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }

    @Override
    public List<Storage> getStorages() {
        return this.controlQuality.getStorages();
    }
}
