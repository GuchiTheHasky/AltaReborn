//package com.alta.web.controller;
//
//import com.alta.dto.StudentDto;
//import com.alta.facade.MainFacade;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class StudentControllerTest {
//    @Mock
//    private MainFacade mainFacade;
//
//    @InjectMocks
//    private StudentController studentController;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    @DisplayName("Integration test findAll() for StudentDto, check status code and content type ")
//    void findAll_shouldTReturnStatusOkAndContentTypeApplicationJson() {
//
//        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
//                "/api/v1/students",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {});
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
//                        .isCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    @DisplayName("Unit test findAll() for StudentDto, check size and values")
//    void findAll_ReturnsListOfStudents() {
//
//        List<StudentDto> students = new ArrayList<>();
//        students.add(new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student", new HashSet<>()));
//        students.add(new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student", new HashSet<>()));
//
//        when(mainFacade.findAllStudents()).thenReturn(students);
//
//        List<StudentDto> result = studentController.findAll();
//
//        verify(mainFacade).findAllStudents();
//
//        assertEquals(2, result.size());
//        assertEquals(students.get(0), result.get(0));
//        assertEquals(students.get(1), result.get(1));
//    }
//}