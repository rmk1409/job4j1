package ru.job4j.thread;

import javafx.scene.shape.Rectangle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 22.09.2019
 */
public class RectangleMove implements Runnable {
    private static final Logger LOG = Logger.getLogger(RectangleMove.class.getName());
    private final Rectangle rect;
    private boolean toTheLeft;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            double currentX = this.rect.getX();
            if (currentX + this.rect.getWidth() >= 300) {
                this.toTheLeft = true;
                LOG.info("Turn to the left");
            } else if (currentX <= 0) {
                this.toTheLeft = false;
                LOG.info("Turn to the right");
            }
            double newX = this.toTheLeft ? currentX - 1 : currentX + 1;
            this.rect.setX(newX);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
