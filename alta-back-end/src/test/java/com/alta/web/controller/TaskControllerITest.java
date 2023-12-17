package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.TaskDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Integration tests for TaskController")
class TaskControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;

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
    @DisplayName("Test, check status code, content type, data saved for save() method")
    void testSaveTaskDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription("text_4");
        taskDto.setAnswer("answer_to_4");
        taskDto.setPathToImage("image_4");
        taskDto.setLevel("level_4");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tasks/save").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        int expectedSize = 4;
        Assertions.assertEquals(expectedSize, taskController.findAll().size());
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 3;
        this.mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }
}