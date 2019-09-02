package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class SchoolTest {
    private School school = new School();
    Student st1 = new Student(10, "st1");
    Student st2 = new Student(20, "st2");
    Student st3 = new Student(30, "st3");
    Student st4 = new Student(40, "st4");
    Student st5 = new Student(50, "st5");
    Student st6 = new Student(60, "st6");
    Student st7 = new Student(70, "st7");
    Student st8 = new Student(80, "st8");
    Student st9 = new Student(90, "st9");
    Student st10 = new Student(100, "st10");
    private List<Student> data = List.of(st10, st2, st8, st4, st5, st6, st7, st3, st9, st1);

    @Test
    public void collectA() {
        List<Student> expected = List.of(st8, st9, st10);
        assertThat(expected, is(this.school.collect(this.data, student -> student.getScore() > 70)));
    }

    @Test
    public void collectB() {
        List<Student> expected = List.of(st6, st7);
        assertThat(expected, is(this.school.collect(this.data, student -> {
            int score = student.getScore();
            return score <= 70 && score > 50;
        })));
    }

    @Test
    public void collectC() {
        List<Student> expected = List.of(st1, st2, st3, st4, st5);
        assertThat(expected, is(this.school.collect(this.data, student -> student.getScore() <= 50)));
    }

    @Test
    public void toMap() {
        Map<String, Student> expected = new HashMap<>();
        expected.put(st10.getLastName(), st10);
        expected.put(st1.getLastName(), st1);
        assertThat(expected, is(this.school.toMap(List.of(st10, st1))));
    }

    @Test
    public void toMapWithDuplicate() {
        Map<String, Student> expected = new HashMap<>();
        expected.put(st10.getLastName(), st10);
        expected.put(st1.getLastName(), st1);
        assertThat(expected, is(this.school.toMap(List.of(st10, st1, st1))));
    }

    @Test
    public void levelOf() {
        List<Student> expected = List.of(st10, st9, st8, st7, st6);
        List<Student> copy = new ArrayList<>(this.data);
        copy.add(2, null);
        copy.add(5, null);
        copy.add(7, null);
        assertThat(expected, is(this.school.levelOf(copy, 55)));
    }
}