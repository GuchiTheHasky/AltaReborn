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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:db/init_zno_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:db/clean_up_zno_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class ZnoControllerITest {

    private static final List<String> TASKS = List.of("/path/to/image1.jpg", "/path/to/image2.jpg");

    private static final String URL_BY_Id = "/api/v1/znos/%d/tasks";
    private static final String URL = "/api/v1/znos";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - should return list of all zno.")
    void shouldReturnListOfAllZno() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(11));
    }

    @Test
    @DisplayName("Test - should return task list by zno id.")
    void shouldReturnTaskListByZnoId() throws Exception {
        int id = 1;
        mockMvc.perform(get(String.format(URL_BY_Id, id)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].imagePath").value(TASKS.get(0)))
                .andExpect(jsonPath("$[1].imagePath").value(TASKS.get(1)))
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    @DisplayName("Test - should return bad request if znoId doesn't exist")
    void shouldReturnBadRequestIfZnoIdDoesntExist() throws Exception {
        int id = 99;
        mockMvc.perform(get(String.format(String.format(URL_BY_Id, id)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
