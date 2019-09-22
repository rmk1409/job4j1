package ru.job4j.thread;

import javafx.scene.shape.Rectangle;

import java.util.logging.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 22.09.2019
 */
public class RectangleMove implements Runnable {
    private static final Logger log = Logger.getLogger(RectangleMove.class.getName());
    private final Rectangle rect;
    private boolean toTheLeft;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            double currentX = this.rect.getX();
            if (currentX + this.rect.getWidth() >= 300) {
                this.toTheLeft = true;
                log.info("Turn to the left");
            } else if (currentX <= 0) {
                this.toTheLeft = false;
                log.info("Turn to the right");
            }
            double newX = this.toTheLeft ? currentX - 1 : currentX + 1;
            this.rect.setX(newX);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
