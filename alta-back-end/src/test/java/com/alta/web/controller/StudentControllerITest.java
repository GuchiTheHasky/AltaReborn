package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.StudentDto;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("Integration tests for StudentController")
class StudentControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentController studentController;

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
    void testSaveStudentDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Sergey");
        studentDto.setLastName("Bondarenko");
        studentDto.setEmail("bondarenko@email");
        studentDto.setGrade("11");
        studentDto.setStatus("offline");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students/save").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        int expectedSize = 4;
        Assertions.assertEquals(expectedSize, studentController.findAll().size());

        StudentDto student = studentController.findByLastName("Bondarenko");
        assertNotNull(student);
        assertThat(student.getGrade()).isEqualTo("11");
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 3;

        this.mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }
}