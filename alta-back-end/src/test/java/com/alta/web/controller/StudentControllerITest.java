package com.alta.web.controller;

import com.alta.dto.StudentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerITest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test findAll() for StudentDto, check status code and content type ")
    void findAll_shouldTReturnStatusOkAndContentTypeApplicationJson() {

        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration test findAllStudentsPageByPage() for StudentDto, check status code and content type")
    void findAllStudentsPageByPage_shouldReturnStatusOkAndContentTypeApplicationJson() {
        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/students/paging?page=0&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<StudentDto> students = responseEntity.getBody();
        assertNotNull(students);
        assertEquals(10, students.size());
    }

    @Test
    @DisplayName("Integration test findAllStudentsPageByPage() for invalid pagination parameters")
    void findAllStudentsPageByPage_invalidPaginationParameters_shouldReturnStatusBadRequest() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                "/api/v1/students/paging?page=-1&size=0",
                HttpMethod.GET,
                null,
                Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Integration test findAllStudentsPageByPage() for retrieving different pages of students")
    void findAllStudentsPageByPage_retrieveDifferentPages_shouldReturnCorrectData() {
        int pageSize = 5;
        int totalStudents = 20;
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        for (int page = 0; page < totalPages; page++) {
            ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                    "/api/v1/students/paging?page=" + page + "&size=" + pageSize,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {});

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            List<StudentDto> students = responseEntity.getBody();
            assertNotNull(students);
            assertTrue(students.size() <= pageSize);
        }
    }

}