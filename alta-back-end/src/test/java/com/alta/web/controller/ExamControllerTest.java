package com.alta.web.controller;

import com.alta.dto.ExamDto;
import com.alta.dto.FullExamDto;
import com.alta.service.ExamService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Log4j2
@DisplayName("ExamController Integration Test")
class ExamControllerTest {

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll() should return list of exams")
    void findAll_ReturnsListOfExams() {
        log.info("Running findAll_ReturnsListOfExams test");

        List<ExamDto> exams = new ArrayList<>();
        exams.add(new ExamDto());
        exams.add(new ExamDto());
        when(examService.findAll()).thenReturn(exams);

        List<ExamDto> result = examController.findAll(0, 10);

        verify(examService).findAll();
        assertEquals(exams.size(), result.size());
    }

    @Test
    @DisplayName("findAll() should return an empty list")
    void findAll_ReturnsEmptyList() {
        log.info("Running findAll_ReturnsEmptyList test");

        when(examService.findAll()).thenReturn(new ArrayList<>());

        List<ExamDto> result = examController.findAll(0, 10);

        verify(examService).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("findById() should return an exam")
    void findById_ReturnsExam() {
        log.info("Running findById_ReturnsExam test");

        int examId = 1;
        FullExamDto examDto = new FullExamDto();
        when(examService.findById(examId)).thenReturn(examDto);

        FullExamDto result = examController.findById(examId);

        verify(examService).findById(examId);
        assertEquals(examDto, result);
    }

    @Test
    @DisplayName("getModelAndView() should return a ModelAndView")
    void getModelAndView_ReturnsModelAndView() {
        log.info("Running getModelAndView_ReturnsModelAndView test");

        int examId = 1;
        String type = "with_answer";
        FullExamDto examDto = new FullExamDto();
        when(examService.findById(examId)).thenReturn(examDto);

        ModelAndView modelAndView = examController.getModelAndView(examId, type);

        verify(examService).findById(examId);
        assertEquals(type, modelAndView.getViewName());
        assertEquals(examDto, modelAndView.getModel().get("exam"));
    }

}
