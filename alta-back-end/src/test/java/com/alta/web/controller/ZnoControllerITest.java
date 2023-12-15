package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.ZnoDto;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for ZnoController.")
class ZnoControllerITest extends AbstractDataBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ZnoController znoController;


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
    void testSaveZnoDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ZnoDto znoDto = new ZnoDto();
        znoDto.setName("ЗНО Завдання за темами з математики");
        znoDto.setYear(2023);
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/znos/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(znoDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        int expectedSize = 4;
        Assertions.assertEquals(expectedSize, znoController.findAll().size());

        ZnoDto zno = znoController.findByName("ЗНО Завдання за темами з математики");
        assertNotNull(zno);
        assertThat(zno.getYear()).isEqualTo(2023);
    }

    @Test
    @Order(2)
    @DisplayName("Test, check status code and content type for findAll() method")
    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
        int expectedSize = 3;

        this.mockMvc.perform(get("/api/v1/znos"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.length()").value(expectedSize));
    }

    @Test
    @Order(3)
    @DisplayName("Test, findAll() for ZnoDto method, check size, values. $ so on")
    void shouldReturnListOfZnoDtoEntities() {
        List<ZnoDto> znoDtoList = znoController.findAll();

        ZnoDto firstZnoDto = znoDtoList.get(0);
        ZnoDto secondZnoDto = znoDtoList.get(1);
        ZnoDto thirdZnoDto = znoDtoList.get(2);

        int expectedFirstId = 1;
        String expectedFirstZno = "ЗНО з математики – демонстраційний варіант";
        int expectedFirstYear = 2021;

        int expectedSecondId = 21;
        String expectedSecondZno = "ЗНО з математики – основна сесія";
        int expectedSecondYear = 2020;

        int expectedThirdId = 41;
        String expectedThirdZno = "ЗНО з математики – додаткова сесія";
        int expectedThirdYear = 2019;

        Assertions.assertNotNull(znoDtoList);

        Assertions.assertEquals(expectedFirstId, firstZnoDto.getId());
        Assertions.assertEquals(expectedFirstZno, firstZnoDto.getName());
        Assertions.assertEquals(expectedFirstYear, firstZnoDto.getYear());

        Assertions.assertEquals(expectedSecondId, secondZnoDto.getId());
        Assertions.assertEquals(expectedSecondZno, secondZnoDto.getName());
        Assertions.assertEquals(expectedSecondYear, secondZnoDto.getYear());

        Assertions.assertEquals(expectedThirdId, thirdZnoDto.getId());
        Assertions.assertEquals(expectedThirdZno, thirdZnoDto.getName());
        Assertions.assertEquals(expectedThirdYear, thirdZnoDto.getYear());
    }
}