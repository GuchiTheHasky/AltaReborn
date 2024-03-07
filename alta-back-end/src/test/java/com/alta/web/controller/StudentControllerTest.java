package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Log4j2
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Unit test findAll() for StudentDto, check size and values")
    void findAll_ReturnsListOfStudents() {

        List<StudentDto> students = new ArrayList<>();
        students.add(new StudentDto(1, "Ткаченко Ігор", "A", "Good student"));
        students.add(new StudentDto(2, "Мельник Карина", "B", "Excellent student"));

        when(studentService.findAll(null, null)).thenReturn(students);

        List<StudentDto> result = studentController.findAll(null, null);

        verify(studentService).findAll(null, null);

        assertEquals(2, result.size());
        assertEquals(students.get(0), result.get(0));
        assertEquals(students.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test findAll() for StudentDto with empty result")
    void findAll_ReturnsEmptyList() {

        when(studentService.findAll(null, null)).thenReturn(Collections.emptyList());

        List<StudentDto> result = studentController.findAll(null, null);

        verify(studentService).findAll(null, null);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Unit test findAll() for StudentDto with exception handling")
    void findAll_HandlesException() {

        when(studentService.findAll(any(Integer.class), any(Integer.class)))
                .thenThrow(new RuntimeException("Error occurred"));

        List<StudentDto> result = studentController.findAll(null, null);

        verify(studentService).findAll(null, null);

        assertTrue(result.isEmpty());
    }
}
