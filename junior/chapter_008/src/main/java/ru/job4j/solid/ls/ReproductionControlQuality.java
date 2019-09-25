package ru.job4j.solid.ls;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Еще одно новое условие. Появились продукты, которые можно переработать после исхода срока годности. Нужно расширить программу. Что бы продукты в флагом canReproduct отправлялись на переработку.
 * Created by roman.pogorelov on 18.09.2019
 */
public class ReproductionControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage reproductionCenter = new Trash();

    public ReproductionControlQuality(ControlQuality controlQuality) {
        super(controlQuality.getStorages());
        this.controlQuality = controlQuality;
        this.setStorages(
                Stream.concat(this.getStorages().stream(), Stream.of(this.reproductionCenter))
                        .collect(Collectors.toList()));
    }

    @Override
    public void sendProduct(Food food) {
        if (food.isCanReproduct() && 1 <= food.getSpoiled()) {
            this.reproductionCenter.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }
}
