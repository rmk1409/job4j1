package ru.job4j.stream;

import java.util.Objects;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class Student {
    private int score;
    private String lastName;

    public Student(int score) {
        this.score = score;
    }

    public Student(int score, String lastName) {
        this.score = score;
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
