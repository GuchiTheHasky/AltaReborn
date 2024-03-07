package com.alta.web.integration;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.web.entity.ExamRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:db/exam_init_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:db/clean_up_exam_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class ExamControllerITest {

    private final static String URL_FIND_ALL = "/api/v1/exams";
    private final static String URL_BY_ID = "/api/v1/exams/%d";

    private final static String URL_EXPORT = "/api/v1/exams/%d/export?type=%s";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - should return all Exams.")
    void shouldReturnAllExams() throws Exception {
        int expectedExamCount = 3;
        String expectedFirstName = "Перший екзамен";
        String expectedSecondName = "Другий екзамен";
        String expectedThirdName = "Третій екзамен";
        mockMvc.perform(get(URL_FIND_ALL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedExamCount))
                .andExpect(jsonPath("$[0].name").value(expectedFirstName))
                .andExpect(jsonPath("$[1].name").value(expectedSecondName))
                .andExpect(jsonPath("$[2].name").value(expectedThirdName));
    }

    @Test
    @DisplayName("Test - should return exam by Id.")
    void shouldReturnExamById() throws Exception {
        int examId = 1;
        mockMvc.perform(get(String.format(URL_BY_ID, examId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(examId))
                .andExpect(jsonPath("$.name").value("Перший екзамен"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Test - should return bad request if Id is out of range.")
    void shouldReturnBadRequestIfIdIsOutOfRange() throws Exception {
        int invalidExamId = 999;
        mockMvc.perform(get(String.format(URL_BY_ID, invalidExamId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test - should return bad request if Id is less than zero.")
    void shouldReturnBadRequestIfIdIsLessThatZero() throws Exception {
        int invalidExamId = -999;
        mockMvc.perform(get(String.format(URL_BY_ID, invalidExamId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test - should create exam new exam.")
    void shouldCreateExamShouldReturnCreatedExam() throws Exception {
        List<StudentDto> students = List.of(
                new StudentDto(1, "Пес Іван", "11", "Відмінник"),
                new StudentDto(2, "Коваль Ольга", "10", "Добре вчиться")
        );

        List<TaskDto> tasks = List.of(
                new TaskDto(1, "/path/to/image1.jpg", "1", "Модуль"),
                new TaskDto(2, "/path/to/image2.jpg", "2", "Планіметрія")
        );

        ExamRequest request = new ExamRequest("Новий Екзамен", students, tasks);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/exams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Новий Екзамен"))
                .andExpect(jsonPath("$.students", hasSize(2)))
                .andExpect(jsonPath("$.students[0].fullName").value("Пес Іван"))
                .andExpect(jsonPath("$.students[1].fullName").value("Коваль Ольга"))
                .andExpect(jsonPath("$.tasks", hasSize(2)))
                .andExpect(jsonPath("$.tasks[0].title").value("Модуль"))
                .andExpect(jsonPath("$.tasks[1].title").value("Планіметрія"));
    }

    @Test
    @DisplayName("Test - should export document without answer.")
    void shouldExportDocumentWithoutAnswer() throws Exception {
        int examId = 1;
        String type = "without_answer";
        mockMvc.perform(get(String.format(URL_EXPORT, examId, type))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exam"))
                .andExpect(model().attribute("exam", hasProperty("name", is("Перший екзамен"))))
                .andExpect(header().string("Content-Type", "text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Test - should export document with answer.")
    void shouldExportDocumentWithAnswer() throws Exception {
        int examId = 1;
        String type = "with_answer";
        mockMvc.perform(get(String.format(URL_EXPORT, examId, type))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exam"))
                .andExpect(model().attribute("exam", hasProperty("name", is("Перший екзамен"))))
                .andExpect(header().string("Content-Type", "text/html;charset=UTF-8"));
    }
}
