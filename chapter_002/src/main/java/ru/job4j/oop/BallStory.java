package ru.job4j.oop;

/**
 * Story about ball
 * Created by roman.pogorelov on 30.08.2019
 */
public class BallStory {
    public static void main(String[] args) {
        Ball ball = new Ball();
        new Hare().tryEat(ball);
        new Wolf().tryEat(ball);
        new Fox().tryEat(ball);
    }
}
