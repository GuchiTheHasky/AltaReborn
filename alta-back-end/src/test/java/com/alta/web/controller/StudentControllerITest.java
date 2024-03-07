package com.alta.web.controller;

import com.alta.dto.StudentDto;
import lombok.extern.log4j.Log4j2;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test for GET /api/v1/students")
    void findAll_shouldReturnStatusOkAndContentTypeApplicationJson() {
        log.info("Running findAll_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<StudentDto> students = responseEntity.getBody();
        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/students with pagination")
    void findAll_withPagination_shouldReturnStatusOkAndCorrectPageSize() {
        log.info("Running findAll_withPagination_shouldReturnStatusOkAndCorrectPageSize test");

        int page = 0;
        int size = 2;

        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                String.format("/api/v1/students?page=%d&size=%d", page, size),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<StudentDto> students = responseEntity.getBody();
        assertNotNull(students);
        assertEquals(size, students.size());
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/students with invalid pagination parameters")
    void getStudentsWithInvalidPaginationParams_shouldReturnStatusBadRequest() {
        log.info("Running getStudentsWithInvalidPaginationParams_shouldReturnStatusBadRequest test");

        ResponseEntity<?> responseEntity = testRestTemplate.exchange(
                "/api/v1/students?page=-1&size=0",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/students with valid pagination parameters")
    void findAll_withValidPaginationParams_shouldReturnStatusOkAndCorrectPageSize() {
        log.info("Running findAll_withValidPaginationParams_shouldReturnStatusOkAndCorrectPageSize test");

        int page = 0;
        int size = 2;

        ResponseEntity<List<StudentDto>> responseEntity = testRestTemplate.exchange(
                String.format("/api/v1/students?page=%d&size=%d", page, size),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<StudentDto> students = responseEntity.getBody();
        assertNotNull(students);
        assertEquals(size, students.size());
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/students with invalid input data")
    void findAll_withInvalidInputData_shouldReturnStatusBadRequest() {
        log.info("Running findAll_withInvalidInputData_shouldReturnStatusBadRequest test");

        ResponseEntity<?> responseEntity = testRestTemplate.exchange(
                "/api/v1/students?page=abc&size=xyz",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
