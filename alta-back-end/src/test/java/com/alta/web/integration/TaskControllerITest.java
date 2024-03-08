package com.alta.web.integration;

import com.alta.dto.TaskDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:db/task_init_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:db/clean_up_task_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TaskControllerITest {

    private static final String URL_BY_TOPIC = "/api/v1/tasks?topics=%d,%d,%d";
    private static final String URL_FOR_UPDATE = "/api/v1/tasks/%d";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - should return list of tasks by topics Ids.")
    void shouldReturnListOfTasksByTopicsIds() throws Exception {
        int expectedLength = 7;
        String expectedImagePath = "/path/to/image3.jpg";

        List<Integer> allTopicIdsIsValid = List.of(1, 2, 3);

        mockMvc.perform(get(String.format(URL_BY_TOPIC, allTopicIdsIsValid.get(0), allTopicIdsIsValid.get(1), allTopicIdsIsValid.get(2)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedLength))
                .andExpect(jsonPath("$[0].imagePath").value(expectedImagePath));
    }

    @Test
    @DisplayName("Test - should return empty list for out of range topic Ids.")
    void shouldReturnEmptyListForOutOfRangeTopicIds() throws Exception {
        List<Integer> outOfRangeTopicIds = List.of(101, 99, 555);
        mockMvc.perform(get(String.format(URL_BY_TOPIC, outOfRangeTopicIds.get(0), outOfRangeTopicIds.get(1), outOfRangeTopicIds.get(2)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test - should return list of tasks if at least one Id is valid.")
    void shouldReturnListOfTasksIfAtLeastOneIdIsValid() throws Exception {
        int expectedLength = 2;
        String firstExpectedImagePath = "/path/to/image1.jpg";
        String secondExpectedImagePath = "/path/to/image3.jpg";

        List<Integer> oneValidId = List.of(1, 99, 98);
        mockMvc.perform(get(String.format(URL_BY_TOPIC, oneValidId.get(0), oneValidId.get(1), oneValidId.get(2)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedLength))
                .andExpect(jsonPath("$[0].imagePath").value(firstExpectedImagePath))
                .andExpect(jsonPath("$[1].imagePath").value(secondExpectedImagePath));
    }

    @Test
    @DisplayName("Test - should return bad request if topics Ids is null.")
    void shouldReturnBadRequestIfTopicsIdsIsNull() throws Exception {
        mockMvc.perform(get(String.format(URL_BY_TOPIC, null, null, null))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test - should update task title with valid Id.")
    void shouldUpdateTaskTitleWithValidId() throws Exception {
        int taskIdToUpdate = 104;
        String updatedTitle = "Подільність";

        TaskDto taskToUpdate = new TaskDto();
        taskToUpdate.setId(taskIdToUpdate);
        taskToUpdate.setImagePath("/new/path/to/image.jpg");
        taskToUpdate.setLevel("2");
        taskToUpdate.setTitle(updatedTitle);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskDtoJson = objectMapper.writeValueAsString(taskToUpdate);

        List<Integer> allTopicIdsIsValid = List.of(1, 2, 3);

        mockMvc.perform(put("/api/v1/tasks/{id}", taskIdToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskIdToUpdate))
                .andExpect(jsonPath("$.title").value(updatedTitle));

        mockMvc.perform(get(String.format(URL_BY_TOPIC, allTopicIdsIsValid.get(0), allTopicIdsIsValid.get(1), allTopicIdsIsValid.get(2)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == %d)].title", taskIdToUpdate).value(updatedTitle));
    }

    @Test
    @DisplayName("Test - should return bad request cause Id is invalid.")
    void shouldReturnBadRequestCauseIdIsInvalid() throws Exception {
        int taskIdToUpdate = 999;
        String updatedTitle = "Подільність";

        TaskDto taskToUpdate = new TaskDto();
        taskToUpdate.setId(taskIdToUpdate);
        taskToUpdate.setImagePath("/new/path/to/image.jpg");
        taskToUpdate.setLevel("2");
        taskToUpdate.setTitle(updatedTitle);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskDtoJson = objectMapper.writeValueAsString(taskToUpdate);

        mockMvc.perform(put(String.format(URL_FOR_UPDATE, taskIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test - should return bad request cause Id is null.")
    void shouldReturnBadRequestCauseIdIsNull() throws Exception {
        String taskIdToUpdate = null;
        String updatedTitle = "Подільність";

        TaskDto taskToUpdate = new TaskDto();
        taskToUpdate.setId(104);
        taskToUpdate.setImagePath("/new/path/to/image.jpg");
        taskToUpdate.setLevel("2");
        taskToUpdate.setTitle(updatedTitle);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskDtoJson = objectMapper.writeValueAsString(taskToUpdate);

        mockMvc.perform(put("/api/v1/tasks/" + taskIdToUpdate, taskToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test - should find all enabled and assigned tasks.")
    void shouldFindAllEnabledAndAssignedTasks() throws Exception {
        List<Integer> studentIds = List.of(1, 2);
        List<Integer> topicIds = List.of(1, 2);
        int expectedEnabledTasksCount = 4;
        int expectedAssignedTasksCount = 1;

        mockMvc.perform(get("/api/v1/tasks")
                        .param("studentIds", studentIds.stream().map(Object::toString).collect(Collectors.joining(",")))
                        .param("topicIds", topicIds.stream().map(Object::toString).collect(Collectors.joining(",")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enabledTasks").isArray())
                .andExpect(jsonPath("$.enabledTasks.length()").value(expectedEnabledTasksCount))
                .andExpect(jsonPath("$.assignedTasks").isArray())
                .andExpect(jsonPath("$.assignedTasks.length()").value(expectedAssignedTasksCount));
    }
}
