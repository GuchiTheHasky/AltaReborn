package com.alta.service.impl;

import com.alta.AbstractDataBase;
import com.alta.entity.Student;
import com.alta.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
class DefaultStudentServiceITest extends AbstractDataBase {

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("Test - should return an empty list if no parameter is passed.")
    void shouldReturnAnEmptyListIfNoParameterIsPassed() {
        List<Student> students = studentService.findAllByIds(Collections.emptyList());
        Assertions.assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("Test - should return an empty list if ids are out of range")
    void shouldReturnAnEmptyListIfIdsAreOutOfRange() {
        List<Integer> outOfRangeIds = List.of(99, 101);
        List<Student> students = studentService.findAllByIds(outOfRangeIds);
        Assertions.assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("Test - should return list of students even if some id is out of range")
    void shouldReturnListOfStudentsEvenIfSomeIdIsOutOfRange() {
        List<Integer> ids = List.of(4, 99);
        List<Student> students = studentService.findAllByIds(ids);
        int expectedSize = 1;

        assertFalse(students.isEmpty());
        assertEquals(expectedSize, students.size());

        assertEquals(getAnna().getId(), students.get(0).getId());
        assertEquals(getAnna().getFirstName(), students.get(0).getFirstName());
        assertEquals(getAnna().getLastName(), students.get(0).getLastName());
        assertEquals(getAnna().getGrade(), students.get(0).getGrade());
        assertEquals(getAnna().getComment(), students.get(0).getComment());
        assertEquals(getAnna().getEmail(), students.get(0).getEmail());
    }

    @Test
    @DisplayName("Test - should return empty list even if ids is null")
    void shouldReturnEmptyListEvenIfIdsIsNull() {
        List<Student> students = studentService.findAllByIds(null);
        Assertions.assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("Test - return list of students by ids.")
    void shouldReturnListOfStudentsById() {
        List<Student> students = studentService.findAllByIds(List.of(1, 4, 7));
        int expectedSize = 3;

        assertFalse(students.isEmpty());
        assertEquals(expectedSize, students.size());

        // First student:
        assertEquals(getIvan().getId(), students.get(0).getId());
        assertEquals(getIvan().getFirstName(), students.get(0).getFirstName());
        assertEquals(getIvan().getLastName(), students.get(0).getLastName());
        assertEquals(getIvan().getGrade(), students.get(0).getGrade());
        assertEquals(getIvan().getComment(), students.get(0).getComment());
        assertEquals(getIvan().getEmail(), students.get(0).getEmail());

        // Second student:
        assertEquals(getAnna().getId(), students.get(1).getId());
        assertEquals(getAnna().getFirstName(), students.get(1).getFirstName());
        assertEquals(getAnna().getLastName(), students.get(1).getLastName());
        assertEquals(getAnna().getGrade(), students.get(1).getGrade());
        assertEquals(getAnna().getComment(), students.get(1).getComment());
        assertEquals(getAnna().getEmail(), students.get(1).getEmail());

        // Third student:
        assertEquals(getOleg().getId(), students.get(2).getId());
        assertEquals(getOleg().getFirstName(), students.get(2).getFirstName());
        assertEquals(getOleg().getLastName(), students.get(2).getLastName());
        assertEquals(getOleg().getGrade(), students.get(2).getGrade());
        assertEquals(getOleg().getComment(), students.get(2).getComment());
        assertEquals(getOleg().getEmail(), students.get(2).getEmail());
    }

    private Student getIvan() {
        return Student.builder()
                .id(1)
                .firstName("Іван")
                .lastName("Пес")
                .grade("11")
                .comment("Відмінник")
                .email("ivan@jmail.com")
                .build();
    }

    private Student getAnna() {
        return Student.builder()
                .id(4)
                .firstName("Анна")
                .lastName("Вафля")
                .grade("11")
                .comment("Відмінниця, активістка")
                .email("anna@jmail.com")
                .build();
    }

    private Student getOleg() {
        return Student.builder()
                .id(7)
                .firstName("Олег")
                .lastName("Князь")
                .grade("9")
                .comment("Покращує свої знання з математики")
                .email("oleg@jmail.com")
                .build();
    }
}