//package com.alta.web.controller;
//
//import com.alta.AbstractDataBase;
//import com.alta.dto.ZnoDto;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@Log4j2
//@Testcontainers
//@SpringBootTest
//@AutoConfigureMockMvc
//@DisplayName("Integration tests for ZnoController.")
//class ZnoControllerITest extends AbstractDataBase {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ZnoController zno;
//
//    @Test
//    @Order(1)
//    @DisplayName("Test, check status code and content type for findAll() method")
//    public void shouldTReturnStatusOkAndContentTypeApplicationJson() throws Exception {
//        int expectedSize = 3;
//
//        this.mockMvc.perform(get("/api/v1/znos"))
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", "application/json"))
//                .andExpect(jsonPath("$.length()").value(expectedSize));
//
//        log.info("Test findAll() method in ZnoControllerITest");
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("Test, findAll() for ZnoDto method, check size, values. $ so on")
//    void shouldReturnListOfZnoDtoEntities() {
//        List<ZnoDto> znoDtoList = zno.findAll();
//
//        ZnoDto firstZnoDto = znoDtoList.get(0);
//        ZnoDto secondZnoDto = znoDtoList.get(1);
//        ZnoDto thirdZnoDto = znoDtoList.get(2);
//
//        int expectedFirstId = 1;
//        String expectedFirstZno = "ЗНО з математики – демонстраційний варіант";
//        int expectedFirstYear = 2021;
//
//        int expectedSecondId = 2;
//        String expectedSecondZno = "ЗНО з математики – основна сесія";
//        int expectedSecondYear = 2020;
//
//        int expectedThirdId = 3;
//        String expectedThirdZno = "ЗНО з математики – додаткова сесія";
//        int expectedThirdYear = 2019;
//
//        Assertions.assertNotNull(znoDtoList);
//
//        Assertions.assertEquals(expectedFirstId, firstZnoDto.getId());
//        Assertions.assertEquals(expectedFirstZno, firstZnoDto.getName());
//        Assertions.assertEquals(expectedFirstYear, firstZnoDto.getYear());
//
//        Assertions.assertEquals(expectedSecondId, secondZnoDto.getId());
//        Assertions.assertEquals(expectedSecondZno, secondZnoDto.getName());
//        Assertions.assertEquals(expectedSecondYear, secondZnoDto.getYear());
//
//        Assertions.assertEquals(expectedThirdId, thirdZnoDto.getId());
//        Assertions.assertEquals(expectedThirdZno, thirdZnoDto.getName());
//        Assertions.assertEquals(expectedThirdYear, thirdZnoDto.getYear());
//    }
//
//}
