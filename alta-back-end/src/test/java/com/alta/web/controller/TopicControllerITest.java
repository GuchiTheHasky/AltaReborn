package com.alta.web.controller;


import com.alta.AbstractDataBase;
import com.alta.dto.TopicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for TopicController")
class TopicControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicController topicController;

    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 2;

        this.mockMvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for save() method")
    void testSaveTopicShouldReturnTopicDtoObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TopicDto topicDto = new TopicDto();
        topicDto.setName("Функції");
        String topicToSave = mapper.writeValueAsString(topicDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topics/save").contentType(MediaType.APPLICATION_JSON)
                        .content(topicToSave))
                        .andExpect(status().isOk())
                        .andExpect(header().string("Content-Type", "application/json"));

        Assertions.assertEquals(3, topicController.findAll().size());
    }
}