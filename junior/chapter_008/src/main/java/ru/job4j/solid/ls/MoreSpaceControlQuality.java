package ru.job4j.solid.ls;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * От хозяина предприятие пришло новое условие требование. На складе Warehouse не хватает место для хранения и поэтому нужно добавить новый склад. Ваше решение?
 * <p>
 * Created by roman.pogorelov on 18.09.2019
 */
public class MoreSpaceControlQuality extends ControlQuality {
    private ControlQuality controlQuality;
    private Storage newWarehouse = new Warehouse();
    private boolean enoughSpace = true;

    public MoreSpaceControlQuality(ControlQuality controlQuality) {
        super(controlQuality.getStorages());
        this.controlQuality = controlQuality;
    }

    @Override
    public void sendProduct(Food food) {
        if (this.enoughSpace && this.getStorages().get(0).getStorage().size() == 10) {
            this.enoughSpace = false;
            this.setStorages(
                    Stream.concat(this.getStorages().stream(), Stream.of(this.newWarehouse))
                            .collect(Collectors.toList()));
        }

        if (!enoughSpace && this.newWarehouse.accept(food)) {
            this.newWarehouse.add(food);
        } else {
            this.controlQuality.sendProduct(food);
        }
    }
}
