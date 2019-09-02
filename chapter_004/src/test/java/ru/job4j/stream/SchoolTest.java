package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class SchoolTest {
    private School school = new School();
    private List<Student> data = List.of(
            new Student(10)
            , new Student(20)
            , new Student(30)
            , new Student(40)
            , new Student(50)
            , new Student(60)
            , new Student(70)
            , new Student(80)
            , new Student(90)
            , new Student(100)
    );

    @Test
    public void collectA() {
        List<Student> expected = List.of(
                new Student(80)
                , new Student(90)
                , new Student(100));
        assertThat(expected, is(this.school.collect(this.data, student -> student.getScore() > 70)));
    }

    @Test
    public void collectB() {
        List<Student> expected = List.of(
                new Student(60)
                , new Student(70));
        assertThat(expected, is(this.school.collect(this.data, student -> {
            int score = student.getScore();
            return score <= 70 && score > 50;
        })));
    }

    @Test
    public void collectC() {
        List<Student> expected = List.of(
                new Student(10)
                , new Student(20)
                , new Student(30)
                , new Student(40)
                , new Student(50));
        assertThat(expected, is(this.school.collect(this.data, student -> student.getScore() <= 50)));
    }
}