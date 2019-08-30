package ru.job4j.oop;

/**
 * Simple class
 * Created by roman.pogorelov on 30.08.2019
 */
public class Cat {
    private String food;
    private String name;

    public static void main(String[] args) {
        System.out.println("There are gav's food.");
        Cat gav = new Cat();
        gav.giveNick("Gav");
        gav.eat("kotleta");
        gav.show();
        System.out.println("There are black's food.");
        Cat black = new Cat();
        black.giveNick("black");
        black.eat("fish");
        black.show();
    }

    public void show() {
        System.out.println(String.format("%s ate %s.", this.name, this.food));
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public void giveNick(String nick) {
        this.name = nick;
    }
}
