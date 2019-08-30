package ru.job4j.oop.inheritance;

/**
 * Child
 * Created by roman.pogorelov on 30.08.2019
 */
public class Dentist extends Doctor {
    private int tooth;

    public int getTooth() {
        return tooth;
    }

    public Tooth removeTooth(Patient patient) {return new Tooth();}
}
