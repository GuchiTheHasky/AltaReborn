//package com.alta.web.controller;
//
//import com.alta.dto.StudentDto;
//import com.alta.dto.TaskDto;
//import com.alta.dto.TasksGroupDto;
//import com.alta.dto.TopicDto;
//
//import com.alta.entity.TasksGroup;
//import com.alta.facade.MainFacade;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//
//import java.time.LocalDate;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class TaskControllerTest {
//    @Mock
//    private MainFacade mainFacade;
//
//    @InjectMocks
//    private TaskController taskController;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    @DisplayName("Integration test findAllTasksIncludedInTopic for TaskDto, check status code and content type")
//    void findAllTasksIncludedInTopic_ReturnsStatusOkAndContentTypeApplicationJson() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        List<StudentDto> students = Arrays.asList(
//                new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student", new HashSet<>()),
//                new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student", new HashSet<>())
//        );
//
//        List<TopicDto> topics = Arrays.asList(new TopicDto(1, "Topic 1"), new TopicDto(2, "Topic 2"));
//
//        TasksRequest request = new TasksRequest(topics, students);
//
//        HttpEntity<TasksRequest> entity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange(
//                "/api/v1/tasks/all",
//                HttpMethod.POST,
//                entity,
//                new ParameterizedTypeReference<>() {
//                });
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
//                .isCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    @DisplayName("Unit test findAllTasksIncludedInTopic, check size and values")
//    void findAllTasksIncludedInTopic_ReturnsListOfTasks() {
//
//        List<StudentDto> students = Arrays.asList(
//                new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student", new HashSet<>()),
//                new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student", new HashSet<>())
//        );
//
//        List<TopicDto> topics = Arrays.asList(new TopicDto(1, "Topic 1"), new TopicDto(2, "Topic 2"));
//
//        TasksRequest request = new TasksRequest(topics, students);
//
//        List<Integer> studentIds = students.stream().map(StudentDto::getId).toList();
//        List<Integer> topicIds = topics.stream().map(TopicDto::getId).toList();
//
//        List<TaskDto> tasks = Arrays.asList(
//                new TaskDto(1, "imagePath1", "level1", "text1", "answer1", "Topic 1", null, new HashSet<>()),
//                new TaskDto(2, "imagePath2", "level2", "text2", "answer2", "Topic 2", null, new HashSet<>())
//        );
//
//        when(mainFacade.findAllTasks(topicIds, studentIds)).thenReturn(tasks);
//
//        List<TaskDto> result = taskController.findAllTasksIncludedInTopic(request);
//
//        verify(mainFacade).findAllTasks(topicIds, studentIds);
//
//        assertEquals(2, result.size());
//        assertEquals(tasks, result);
//    }
//
//    @Test
//    @DisplayName("Integration test assignTasksToStudents, check status code and content type")
//    void assignTasksToStudents_ReturnsStatusOkAndContentTypeApplicationJson() {
//
//        List<StudentDto> students = Arrays.asList(
//                new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student", new HashSet<>()),
//                new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student", new HashSet<>())
//        );
//
//        List<TaskDto> tasks = Arrays.asList(
//                new TaskDto(1, "imagePath1", "level1", "text1", "answer1", "Topic 1", null, new HashSet<>()),
//                new TaskDto(2, "imagePath2", "level2", "text2", "answer2", "Topic 2", null, new HashSet<>())
//        );
//
//        ModelRequest requestBody = new ModelRequest(tasks, students);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<ModelRequest> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<List<TasksAssignmentResponse>> responseEntity = testRestTemplate.exchange(
//                "/api/v1/tasks/assign",
//                HttpMethod.POST,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
//                .isCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    @DisplayName("Unit test assignTasksToStudents, check size and values")
//    void assignTasksToStudents_ReturnsApplicationJson() {
//
//        List<StudentDto> students = Arrays.asList(
//                new StudentDto(1, "Ткаченко Ігор", "igor.tkachenko@example.com", "A", "Good student", new HashSet<>()),
//                new StudentDto(2, "Мельник Карина", "karina.melnik@example.com", "B", "Excellent student", new HashSet<>())
//        );
//
//        List<TaskDto> tasks = Arrays.asList(
//                new TaskDto(1, "imagePath1", "level1", "text1", "answer1", "Topic 1", null, new HashSet<>()),
//                new TaskDto(2, "imagePath2", "level2", "text2", "answer2", "Topic 2", null, new HashSet<>())
//        );
//
//        LocalDate creationDate = LocalDate.now();
//
//        TasksGroupDto tasksGroup = new TasksGroupDto(1, creationDate, tasks);
//
//        Map<StudentDto, TasksGroupDto> expectedResponseMap = students.stream()
//                .collect(Collectors.toMap(student -> student, student -> tasksGroup));
//
//        List<TasksAssignmentResponse> expectedResponse = expectedResponseMap.entrySet().stream()
//                .map(entry -> new TasksAssignmentResponse(entry.getKey(), entry.getValue()))
//                .toList();
//
//        when(mainFacade.assignTasksToStudents(students, tasks)).thenReturn(expectedResponseMap);
//
//        ModelRequest requestBody = new ModelRequest(tasks, students);
//
//        List<TasksAssignmentResponse> actualResponse = taskController.assignTasksToStudents(requestBody);
//
//        verify(mainFacade).assignTasksToStudents(students, tasks);
//
//        assertNotNull(actualResponse);
//        assertEquals(expectedResponse.size(), actualResponse.size());
//
//        assertEquals(expectedResponse, actualResponse);
//    }
//}