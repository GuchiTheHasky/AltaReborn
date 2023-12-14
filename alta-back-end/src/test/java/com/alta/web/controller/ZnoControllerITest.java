package com.alta.web.controller;

import com.alta.AbstractDataBase;
import com.alta.dto.ZnoDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for ZnoController.")
@Transactional
class ZnoControllerITest extends AbstractDataBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ZnoController znoController;


    @Test
    @Order(1)
    @DisplayName("Test, check status code and content type for save() method")
    void testSaveZnoDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ZnoDto znoDto = new ZnoDto();
        znoDto.setName("ЗНО з математики – основна сесія");
        znoDto.setYear(2023);
        String znoToSave = mapper.writeValueAsString(znoDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/znos/save").contentType(MediaType.APPLICATION_JSON)
                        .content(znoToSave))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));

        Assertions.assertEquals(4, znoController.findAll().size());
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