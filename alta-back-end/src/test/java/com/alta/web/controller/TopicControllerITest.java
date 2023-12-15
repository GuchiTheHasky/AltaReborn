package com.alta.web.controller;


import com.alta.AbstractDataBase;
import com.alta.dto.TopicDto;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Integration tests for TopicController")
class TopicControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicController topicController;

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
    @DisplayName("Test, check status code, content type, data saving for save() method")
    void testSaveTopicDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TopicDto topicDto = new TopicDto();
        topicDto.setName("Функції");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topics/save").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(topicDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, topicController.findAll().size());
        assertNotNull(topicController.findByName("Функції"));
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 2;

        this.mockMvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }
}