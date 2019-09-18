package ru.job4j.solid.ls;

import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class MoreSpaceControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage newWarehouse = new Warehouse();
    private boolean notEnoughSpace;

    public MoreSpaceControlQuality(ControlQuality controlQuality) {
        this.controlQuality = controlQuality;
    }

    @Override
    public void sendProduct(Food food) {
        if (!this.notEnoughSpace && this.controlQuality.getStorages().get(0).getStorage().size() == 10) {
            this.notEnoughSpace = true;
            this.controlQuality.getStorages().add(this.newWarehouse);
        }

        if (notEnoughSpace && this.newWarehouse.accept(food)) {
            this.newWarehouse.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }

    @Override
    public List<Storage> getStorages() {
        return this.controlQuality.getStorages();
    }
}
