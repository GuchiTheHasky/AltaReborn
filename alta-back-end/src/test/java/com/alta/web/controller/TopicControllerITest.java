package com.alta.web.controller;

import com.alta.dto.TopicDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test for GET /api/v1/topics")
    void getAllTopics_shouldReturnStatusOkAndContentTypeApplicationJson() {
        log.info("Running getAllTopics_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<List<TopicDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/topics",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/topics with pagination")
    void getTopicsWithPagination_shouldReturnStatusOkAndContentTypeApplicationJson() {
        log.info("Running getTopicsWithPagination_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<List<TopicDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/topics?page=1&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/topics with invalid pagination parameters")
    void getTopicsWithInvalidPaginationParams_shouldReturnStatusBadRequest() {
        log.info("Running getTopicsWithInvalidPaginationParams_shouldReturnStatusBadRequest test");

        ResponseEntity<?> responseEntity = testRestTemplate.exchange(
                "/api/v1/topics?page=-1&size=0",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}