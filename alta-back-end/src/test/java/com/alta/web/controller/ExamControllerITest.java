package com.alta.web.controller;

import com.alta.dto.ExamDto;
import com.alta.dto.FullExamDto;
import com.alta.service.ExamService;
import com.alta.web.entity.ExamRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExamControllerITest {

    @Mock
    private ExamService examService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test for GET /api/v1/exams")
    void findAll_shouldReturnStatusOkAndContentTypeApplicationJson() {
        log.info("Running findAll_shouldReturnStatusOkAndContentTypeApplicationJson test");

        ResponseEntity<List<ExamDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/exams",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/exams/{id}")
    void findById_shouldReturnStatusOkAndExamDto() {
        log.info("Running findById_shouldReturnStatusOkAndExamDto test");

        ResponseEntity<FullExamDto> responseEntity = testRestTemplate.getForEntity(
                "/api/v1/exams/{id}", FullExamDto.class, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        FullExamDto examDto = responseEntity.getBody();
        assertNotNull(examDto);
    }

    @Test
    @DisplayName("Integration test for POST /api/v1/exams")
    void createExam_shouldReturnStatusOkAndExamDto() {
        log.info("Running createExam_shouldReturnStatusOkAndExamDto test");

        ExamRequest examRequest = new ExamRequest("Sample Exam", List.of(), List.of());
        HttpEntity<ExamRequest> requestEntity = new HttpEntity<>(examRequest);

        ResponseEntity<FullExamDto> responseEntity = testRestTemplate.exchange(
                "/api/v1/exams", HttpMethod.POST, requestEntity, FullExamDto.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        FullExamDto examDto = responseEntity.getBody();
        assertNotNull(examDto);
    }

    @Test
    @DisplayName("Integration test for GET /api/v1/exams/{id}/export")
    void getModelAndView_shouldReturnStatusOkAndCorrectViewName() {
        log.info("Running getModelAndView_shouldReturnStatusOkAndCorrectViewName test");

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(
                "/api/v1/exams/{id}/export?type={type}", String.class, 1, "with_answer");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}
