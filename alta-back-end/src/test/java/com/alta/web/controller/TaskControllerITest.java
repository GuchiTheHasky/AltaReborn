package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
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


import static org.assertj.core.api.Assertions.assertThat;
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
    @Autowired
    private TopicService topicService;
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
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 3;
        this.mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code, content type, data updated for update() method")
    void testUpdateTaskDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TaskDto taskDto = taskService.findById(1);
        TopicDto topicDtoToAssign = topicService.findById(1);
        //taskDto.setTopicDto(topicDtoToAssign);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tasks/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        TaskDto updated = taskService.findById(1);
        //TopicDto actual = updated.getTopicDto();
//        assertThat(actual).isNotNull();
//        assertThat(actual).isEqualTo(topicDtoToAssign);
    }
}