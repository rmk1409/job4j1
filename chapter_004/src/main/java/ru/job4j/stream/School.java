package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .takeWhile(o -> o.getScore() > bound)
                .collect(Collectors.toList());
    }
}
