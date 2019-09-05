package ru.job4j.map.model;

import java.util.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, children, birthday);
//    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        map.put(new User("Ivan", 5, new GregorianCalendar(1987, 9, 14)), "First Value");
        map.put(new User("Ivan", 5, new GregorianCalendar(1987, 9, 14)), "Second Value");
        map.put(new User("Ivan", 5, new GregorianCalendar(1987, 9, 14)), "Third Value");
        map.put(new User("Ivan", 5, new GregorianCalendar(1987, 9, 14)), "Fourth Value");
        map.forEach((k, v) -> System.out.printf("Key(%s), Value(%s)%n", k, v));
    }
}
