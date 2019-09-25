package ru.job4j.solid.ls;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Еще одно требование. Овощи пришедшие на обработку и попадающие на клад. Должны храниться в специальном складе с низкой температурой. Ваши решения.
 * <p>
 * Created by roman.pogorelov on 18.09.2019
 */
public class FridgeControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage fridge = new Trash();

    public FridgeControlQuality(ControlQuality controlQuality) {
        super(controlQuality.getStorages());
        this.controlQuality = controlQuality;
        this.setStorages(
                Stream.concat(this.getStorages().stream(), Stream.of(this.fridge))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void sendProduct(Food food) {
        if (food.isVegetable() && 0.25 > food.getSpoiled()) {
            fridge.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }
}
