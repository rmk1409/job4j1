package ru.job4j.oop;

/**
 * Simple class
 * Created by roman.pogorelov on 30.08.2019
 */
public class Jukebox {
    public static void main(String[] args) {
        Jukebox jukebox = new Jukebox();
        jukebox.music(0);
        jukebox.music(1);
        jukebox.music(2);
    }

    public void music(int position) {
        String words = "Песня не найдена";
        if (position == 1) {
            words = "Пусть бегут неуклюже";
        } else if (position == 2) {
            words = "Спокойной ночи";
        }
        System.out.println(words);
    }
}
