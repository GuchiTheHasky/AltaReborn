package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.facade.MainFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {
    @Mock
    private MainFacade mainFacade;

    @InjectMocks
    private StudentController studentController;

    @Test
    @DisplayName("Unit test findAll() for StudentDto, check size and values")
    void findAll_ReturnsListOfStudents() {

        List<StudentDto> students = new ArrayList<>();
        students.add(new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student"));
        students.add(new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student"));

        when(mainFacade.findAllStudents()).thenReturn(students);

        List<StudentDto> result = studentController.findAll();

        verify(mainFacade).findAllStudents();

        assertEquals(2, result.size());
        assertEquals(students.get(0), result.get(0));
        assertEquals(students.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test findAllStudentsPageByPage() for StudentDto, check size and values")
    void findAllStudentsPageByPage_ReturnsListOfStudents() {

        PageRequest pageRequest = PageRequest.of(0, 10);

        List<StudentDto> students = new ArrayList<>();
        students.add(new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student"));
        students.add(new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student"));

        when(mainFacade.findAllStudentsPageByPage(pageRequest)).thenReturn(students);

        List<StudentDto> result = studentController.findAllStudentsPageByPage(0, 10);

        verify(mainFacade).findAllStudentsPageByPage(pageRequest);

        assertEquals(2, result.size());
        assertEquals(students.get(0), result.get(0));
        assertEquals(students.get(1), result.get(1));
    }

}