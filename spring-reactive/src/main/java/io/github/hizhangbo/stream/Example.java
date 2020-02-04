package io.github.hizhangbo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Bob
 * @date 2020-02-01 15:41
 */
public class Example {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("James", 18, GENDER.MALE, GRADE.ONE),
                new Student("Kobe", 19, GENDER.MALE, GRADE.FOUR),
                new Student("Curry", 20, GENDER.MALE, GRADE.ONE),
                new Student("Nash", 21, GENDER.MALE, GRADE.THREE));

        Map<GRADE, Long> groupByGrade = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println(groupByGrade);
    }

    @Data
    @AllArgsConstructor
    private static class Student {
        private String name;
        private Integer age;
        private GENDER gender;
        private GRADE grade;
    }

    enum GENDER {
        MALE,
        FEMALE
    }

    enum GRADE {
        ONE,
        TWO,
        THREE,
        FOUR
    }
}