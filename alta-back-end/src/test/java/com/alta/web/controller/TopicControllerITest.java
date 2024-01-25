package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.TopicDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
        initSqlScript();
    }


    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for findAll() method for TopicDto")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 2;

        this.mockMvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }

    @Test
    @Order(2)
    @DisplayName("Test findAll() for TopicDto, check size and values")
    void shouldReturnListOfTopicDtoEntities() {
        List<TopicDto> topicDtoList = topicController.findAll();

        TopicDto firstTopicDto = topicDtoList.get(0);
        TopicDto secondTopicDto = topicDtoList.get(1);

        int expectedFirstId = 1;
        String expectedFirstTopicTitle = "Числа і вирази";

        int expectedSecondId = 21;
        String expectedSecondTopicTitle = "Рівняння, нерівності та їхні системи";

        Assertions.assertNotNull(topicDtoList);

        Assertions.assertEquals(expectedFirstId, firstTopicDto.getId());
        Assertions.assertEquals(expectedFirstTopicTitle, firstTopicDto.getTitle());

        Assertions.assertEquals(expectedSecondId, secondTopicDto.getId());
        Assertions.assertEquals(expectedSecondTopicTitle, secondTopicDto.getTitle());
    }
}