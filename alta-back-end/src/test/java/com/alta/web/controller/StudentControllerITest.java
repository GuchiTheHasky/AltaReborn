package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.StudentDto;
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
        initSqlScript();
    }


    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for findAll() method for StudentDto")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 3;

        this.mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }

    @Test
    @Order(2)
    @DisplayName("Test findAll() for StudentDto, check size and values")
    void shouldReturnListOfStudentDtoEntities() {
        List<StudentDto> studentDtoList = studentController.findAll();

        StudentDto firstStudentDto = studentDtoList.get(0);
        StudentDto secondStudentDto = studentDtoList.get(1);
        StudentDto thirdStudentDto = studentDtoList.get(2);

        int expectedFirstId = 1;
        String expectedFirstStudentFullName = "Ivanova Anna";
        String expectedFirstStudentEmail = "burova@email";
        String expectedFirstStudentGrade = "10";
        String expectedFirstStudentComment = "offline";

        int expectedSecondId = 21;
        String expectedSecondStudentFullName = "Boiko Olga";
        String expectedSecondStudentEmail = "boyko@email";
        String expectedSecondStudentGrade = "9";
        String expectedSecondStudentComment = "online";

        int expectedThirdId = 41;
        String expectedThirdStudentFullName = "Moroz Anastasiya";
        String expectedThirdStudentEmail = "moroz@email";
        String expectedThirdStudentGrade = "11";
        String expectedThirdStudentComment = "offline";

        Assertions.assertNotNull(studentDtoList);

        Assertions.assertEquals(expectedFirstId, firstStudentDto.getId());
        Assertions.assertEquals(expectedFirstStudentFullName, firstStudentDto.getFullName());
        Assertions.assertEquals(expectedFirstStudentEmail, firstStudentDto.getEmail());
        Assertions.assertEquals(expectedFirstStudentGrade, firstStudentDto.getGrade());
        Assertions.assertEquals(expectedFirstStudentComment, firstStudentDto.getComment());

        Assertions.assertEquals(expectedSecondId, secondStudentDto.getId());
        Assertions.assertEquals(expectedSecondStudentFullName, secondStudentDto.getFullName());
        Assertions.assertEquals(expectedSecondStudentEmail, secondStudentDto.getEmail());
        Assertions.assertEquals(expectedSecondStudentGrade, secondStudentDto.getGrade());
        Assertions.assertEquals(expectedSecondStudentComment, secondStudentDto.getComment());

        Assertions.assertEquals(expectedThirdId, thirdStudentDto.getId());
        Assertions.assertEquals(expectedThirdStudentFullName, thirdStudentDto.getFullName());
        Assertions.assertEquals(expectedThirdStudentEmail, thirdStudentDto.getEmail());
        Assertions.assertEquals(expectedThirdStudentGrade, thirdStudentDto.getGrade());
        Assertions.assertEquals(expectedThirdStudentComment, thirdStudentDto.getComment());
    }
}