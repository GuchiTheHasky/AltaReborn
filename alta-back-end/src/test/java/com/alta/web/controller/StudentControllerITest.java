package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.StudentDto;
import jakarta.transaction.Transactional;
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
@DisplayName("Integration tests for StudentController")
@Transactional
class StudentControllerITest extends AbstractDataBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentController studentController;


    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for save() method")
    void testSaveStudentDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Sergey");
        studentDto.setLastName("Bondarenko");
        studentDto.setEmail("bondarenko@email");
        studentDto.setGrade("11");
        studentDto.setStatus("offline");
        String studentToSave = mapper.writeValueAsString(studentDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students/save").contentType(MediaType.APPLICATION_JSON)
                        .content(studentToSave))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
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