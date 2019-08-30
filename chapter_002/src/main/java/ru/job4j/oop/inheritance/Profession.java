package ru.job4j.oop.inheritance;

import java.util.Date;

/**
 * Base class
 * Created by roman.pogorelov on 30.08.2019
 */
public class Profession {
    private String name;
    private String surname;
    private String education;
    private Date birthday;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEducation() {
        return education;
    }

    public Date getBirthday() {
        return birthday;
    }
}
