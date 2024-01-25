package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.facade.MainFacade;
import com.alta.service.TaskService;
import com.alta.web.entity.TasksRequest;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Integration tests for TaskController")
class TaskControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainFacade mainFacade;

    @Autowired
    private TaskService taskService;


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeAll
    static void init() {
        AbstractDataBase.initSqlScript();
    }

    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for findAllUnfinishedTasks() method, check values")
    public void testFindAllUnfinishedTasks() throws Exception {
        TasksRequest request = new TasksRequest();
        request.setTopics(Arrays.asList(1, 21));
        request.setStudents(Arrays.asList(1, 21, 41));

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tasks/unfinished")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for findAllWithAnswer() for TaskDto method, check values")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        List<Integer> tasks = Arrays.asList(1, 21, 41, 61);
        List<Integer> studentsIds = Arrays.asList(1, 21, 41);

        List<Task> tasksOfStudentsForNow = mainFacade.findAllStudentsById(studentsIds).stream()
                .flatMap(student -> student.getTasks().stream()).toList();

        assertTrue(tasksOfStudentsForNow.isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tasks/answers")
                        .param("tasks", "1", "21", "41", "61")
                        .param("student", "1", "21", "41"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("task_list_answers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tasksWithAnswers"));

        List<Integer> tasksAssignedToStudents = mainFacade.findAllStudentsById(studentsIds).stream()
                .flatMap(student -> student.getTasks().stream())
                .map(Task::getId)
                .distinct()
                .toList();

        Assertions.assertEquals(tasks, tasksAssignedToStudents);
    }
}
