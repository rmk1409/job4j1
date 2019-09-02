package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    public Map<String, Student> toMap(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(Student::getLastName, i -> i));
    }
}
