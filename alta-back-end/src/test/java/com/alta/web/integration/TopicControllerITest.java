package com.alta.web.integration;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:db/init_topics_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:db/clean_up_topics_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TopicControllerITest {

    private static final List<String> SORTED_ACTUAL_TOPICS = List.of("Арифметичний корінь", "Дробові вирази", "Дробово-раціональні рівняння", "Задачі на рух, відсотки...", "Квадратні рівняння", "Лінійні рівняння", "Логарифм", "Подільність", "Рівняння з модулем", "Степінь", "Числові множини");
    private static final String URL_FOR_PAGE = "/api/v1/topics?page=%d&size=%d";
    private static final String URL = "/api/v1/topics";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - find all topics without pagination.")
    void shouldReturnAllTopicsWithoutPagination() throws Exception {
        mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(SORTED_ACTUAL_TOPICS.get(0)))
                .andExpect(jsonPath("$[1].title").value(SORTED_ACTUAL_TOPICS.get(1)))
                .andExpect(jsonPath("$[2].title").value(SORTED_ACTUAL_TOPICS.get(2)))
                .andExpect(jsonPath("$[3].title").value(SORTED_ACTUAL_TOPICS.get(3)))
                .andExpect(jsonPath("$[4].title").value(SORTED_ACTUAL_TOPICS.get(4)))
                .andExpect(jsonPath("$[5].title").value(SORTED_ACTUAL_TOPICS.get(5)))
                .andExpect(jsonPath("$[6].title").value(SORTED_ACTUAL_TOPICS.get(6)))
                .andExpect(jsonPath("$[7].title").value(SORTED_ACTUAL_TOPICS.get(7)))
                .andExpect(jsonPath("$[8].title").value(SORTED_ACTUAL_TOPICS.get(8)))
                .andExpect(jsonPath("$[9].title").value(SORTED_ACTUAL_TOPICS.get(9)))
                .andExpect(jsonPath("$[10].title").value(SORTED_ACTUAL_TOPICS.get(10)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(11));
    }

    @Test
    @DisplayName("Test - find all topics, with pagination.")
    void shouldReturnTopicsWithPagination() throws Exception {
        int firstPage = 0;
        int secondPage = 1;
        int pageSize = 5;

        mockMvc.perform(get(String.format(URL_FOR_PAGE, firstPage, pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(SORTED_ACTUAL_TOPICS.get(0)))
                .andExpect(jsonPath("$[1].title").value(SORTED_ACTUAL_TOPICS.get(1)))
                .andExpect(jsonPath("$[2].title").value(SORTED_ACTUAL_TOPICS.get(2)))
                .andExpect(jsonPath("$[3].title").value(SORTED_ACTUAL_TOPICS.get(3)))
                .andExpect(jsonPath("$[4].title").value(SORTED_ACTUAL_TOPICS.get(4)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));

        mockMvc.perform(get(String.format(URL_FOR_PAGE, secondPage, pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(SORTED_ACTUAL_TOPICS.get(5)))
                .andExpect(jsonPath("$[1].title").value(SORTED_ACTUAL_TOPICS.get(6)))
                .andExpect(jsonPath("$[2].title").value(SORTED_ACTUAL_TOPICS.get(7)))
                .andExpect(jsonPath("$[3].title").value(SORTED_ACTUAL_TOPICS.get(8)))
                .andExpect(jsonPath("$[4].title").value(SORTED_ACTUAL_TOPICS.get(9)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    @DisplayName("Test - find all topics, page by page with empty list in the end.")
    void shouldReturnSortedStudentsListPageByPageAndEmptyList() throws Exception {
        int firstPage = 0;
        int secondPage = 1;
        int thirdPage = 2;
        int pageSize = 10;

        mockMvc.perform(get(String.format(URL_FOR_PAGE, firstPage, pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(10));

        mockMvc.perform(get(String.format(URL_FOR_PAGE, secondPage, pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        mockMvc.perform(get(String.format(URL_FOR_PAGE, thirdPage, pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Test - throw exception, if page or size value is invalid.")
    void shouldThrowBadRequestExceptionIfPageOrSizeValueIsInvalid() throws Exception {
        int zero = 0;
        int invalidValue = -1;
        int invalidMinValue = Integer.MIN_VALUE;
        int invalidMaxValue = Integer.MAX_VALUE;

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidMaxValue, invalidMaxValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidMinValue, invalidMinValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidMinValue, invalidMaxValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidMaxValue, invalidMinValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, zero, invalidValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidValue, invalidValue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(String.format(URL_FOR_PAGE, invalidValue, zero))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

