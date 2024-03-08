package com.alta.web.controller;

import com.alta.dto.ZnoDto;
import lombok.extern.log4j.Log4j2;
import com.alta.dto.TaskDto;
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
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ZnoControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test for GET /api/v1/znos")
    void getAll_shouldReturnStatusOkAndContentTypeApplicationJson() {
        log.info("Running getAll_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<List<ZnoDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/znos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("Integration test for GET /api/v1/znos/{id}/tasks")
    void findTasksByZnoId_shouldReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        log.info("Running findTasksByZnoId_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(
                "/api/v1/znos/{id}/tasks", String.class, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        String responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        List<TaskDto> tasks = objectMapper.readValue(responseBody, new TypeReference<List<TaskDto>>() {});
        assertNotNull(tasks);
    }

    @Test
    @DisplayName("Integration test for handling ZnoException: ZNO not found")
    void handleZnoException_ZnoNotFound() {
        log.info("Running handleZnoException_ZnoNotFound test");

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/znos/999/tasks", String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        assertEquals("ZNO not found for ID: 999", responseEntity.getBody());
    }

    @Test
    @DisplayName("Integration test for handling validation errors: Invalid ZNO ID (not positive)")
    void handleValidationErrors_NotPositiveZnoId() {
        log.info("Running handleValidationErrors_NotPositiveZnoId test");

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/znos/-1/tasks", String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        assertEquals("ZNO not found for ID: -1", responseEntity.getBody());
    }
}
