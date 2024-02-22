package com.alta.web.controller;

import com.alta.dto.TopicDto;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test findAll() for TopicDto, check status code and content type ")
    void findAll_shouldReturnStatusOkAndContentTypeApplicationJson() {
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
    @DisplayName("Integration test findAllTopicsPageByPage() for TopicDto, check status code and content type")
    void findAllTopicsPageByPage_shouldReturnStatusOkAndContentTypeApplicationJson() {
        ResponseEntity<List<TopicDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/topics/paging?page=0&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<TopicDto> topics = responseEntity.getBody();
        assertNotNull(topics);
        assertEquals(10, topics.size());
    }

    @Test
    @DisplayName("Integration test findAllTopicsPageByPage() for invalid pagination parameters")
    void findAllTopicsPageByPage_invalidPaginationParameters_shouldReturnStatusBadRequest() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                "/api/v1/topics/paging?page=-1&size=0",
                HttpMethod.GET,
                null,
                Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Integration test findAllTopicsPageByPage() for retrieving different pages of topics")
    void findAllTopicsPageByPage_retrieveDifferentPages_shouldReturnCorrectData() {
        int pageSize = 5;
        int totalTopics = 20;
        int totalPages = (int) Math.ceil((double) totalTopics / pageSize);

        for (int page = 0; page < totalPages; page++) {
            ResponseEntity<List<TopicDto>> responseEntity = testRestTemplate.exchange(
                    "/api/v1/topics/paging?page=" + page + "&size=" + pageSize,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {});

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            List<TopicDto> topics = responseEntity.getBody();
            assertNotNull(topics);
            assertTrue(topics.size() <= pageSize);
        }
    }
}