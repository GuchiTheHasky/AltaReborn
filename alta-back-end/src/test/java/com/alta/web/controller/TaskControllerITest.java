package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.TaskRequest;
import com.alta.web.entity.TaskResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerITest {

    @MockBean
    private MainFacade mainFacade;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Integration test findAll() for TaskDto, check status code and content type")
    void findAll_shouldReturnStatusOkAndContentTypeApplicationJson() {
        ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/tasks?studentIds=1,2,3&topicIds=4,5,6",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration test findAllTasksPageByPage() for TaskDto, check status code and content type")
    void findAllTasksPageByPage_shouldReturnStatusOkAndContentTypeApplicationJson() {
        ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange(
                "/api/v1/tasks/paging?studentIds=1,2,3&topicIds=4,5,6&page=0&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<TaskDto> tasks = responseEntity.getBody();
        assertNotNull(tasks);
    }

    @Test
    @DisplayName("Integration test findAllTasksPageByPage() for invalid pagination parameters")
    void findAllTasksPageByPage_invalidPaginationParameters_shouldReturnStatusBadRequest() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                "/api/v1/tasks/paging?studentIds=1,2,3&topicIds=4,5,6&page=-1&size=0",
                HttpMethod.GET,
                null,
                Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Integration test findAllTasksPageByPage() for retrieving different pages of tasks")
    void findAllTasksPageByPage_retrieveDifferentPages_shouldReturnCorrectData() {
        int pageSize = 5;
        int totalTasks = 20;
        int totalPages = (int) Math.ceil((double) totalTasks / pageSize);

        for (int page = 0; page < totalPages; page++) {
            ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange(
                    "/api/v1/tasks/paging?studentIds=1,2,3&topicIds=4,5,6&page=" + page + "&size=" + pageSize,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {});

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            List<TaskDto> tasks = responseEntity.getBody();
            assertNotNull(tasks);
            assertTrue(tasks.size() <= pageSize);
        }
    }

    @Test
    @DisplayName("Integration test receiveAssignmentTasks() for TaskResponse, check status code and content type")
    void receiveAssignmentTasks_shouldReturnStatusOkAndContentTypeApplicationJson() {
        List<Integer> studentIds = Arrays.asList(1, 2);
        List<Integer> tasksIds = Arrays.asList(1, 2);
        TaskRequest request = new TaskRequest(studentIds, tasksIds);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TaskRequest> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<List<TaskResponse>> responseEntity = testRestTemplate.exchange(
                "/api/v1/tasks/assign",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
                .isCompatibleWith(MediaType.APPLICATION_JSON));

        List<TaskResponse> taskResponses = responseEntity.getBody();
        assertNotNull(taskResponses);
    }

    @Test
    @DisplayName("Integration test update() for TaskDto, check status code and content type")
    void update_shouldReturnStatusOkAndContentTypeApplicationJson() {

        TaskDto updatedTask = new TaskDto(1, "image_updated.jpg", "Updated_Level", "updated_text", "Updated_Answer", "Updated_Title");

         when(mainFacade.updateTask(anyInt(), any(TaskDto.class))).thenReturn(updatedTask);

        ResponseEntity<TaskDto> responseEntity = testRestTemplate.exchange(
                "/api/v1/tasks/1",
                HttpMethod.PUT,
                new HttpEntity<>(updatedTask),
                TaskDto.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(updatedTask, responseEntity.getBody());
    }

}