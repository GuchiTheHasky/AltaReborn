package com.alta.web.integration;

import com.alta.AbstractDataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
class StudentControllerITest extends AbstractDataBase {

    private static final List<String> SORTED_ACTUAL_STUDENTS_FULL_NAMES = List.of("Вареник Юлія", "Вафля Анна", "Голубець Тетяна", "Жук Дмитро", "Князь Олег", "Криса Олександр", "Пес Іван", "Сало Василина", "Синяк Софія", "Стакан Василь", "Шостий Микита");

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test - find all students, without pagination.")
    void shouldReturnSortedStudentsListWithoutPagination() throws Exception {
        mockMvc.perform(get("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(0)))
                .andExpect(jsonPath("$[1].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(1)))
                .andExpect(jsonPath("$[2].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(2)))
                .andExpect(jsonPath("$[3].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(3)))
                .andExpect(jsonPath("$[4].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(4)))
                .andExpect(jsonPath("$[5].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(5)))
                .andExpect(jsonPath("$[6].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(6)))
                .andExpect(jsonPath("$[7].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(7)))
                .andExpect(jsonPath("$[8].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(8)))
                .andExpect(jsonPath("$[9].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(9)))
                .andExpect(jsonPath("$[10].fullName").value(SORTED_ACTUAL_STUDENTS_FULL_NAMES.get(10)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(11));
    }
}
