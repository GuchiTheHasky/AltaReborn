package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.TaskDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for TaskController")
@Transactional
class TaskControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;


    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for save() method")
    void testSaveTaskDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TaskDto taskDto = new TaskDto();
        taskDto.setNumber(40);
        taskDto.setImagePath("image_4");
        taskDto.setLevel("level_4");
        taskDto.setText("text_4");
        taskDto.setAnswer("answer_to_4");
        String taskToSave = mapper.writeValueAsString(taskDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tasks/save").contentType(MediaType.APPLICATION_JSON)
                        .content(taskToSave))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
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