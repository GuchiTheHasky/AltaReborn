package com.alta.web.controller;

import com.alta.dto.ExamDto;
import com.alta.dto.FullExamDto;
import com.alta.service.ExamService;
import com.alta.web.entity.ExamRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.alta.web.util.PageableValidator.pageableValidation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping
    @Operation(summary = "Get all exams.", tags = "Exam")
    public List<ExamDto> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        pageableValidation(page, size);
        return examService.findAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find an exam by Id.", tags = "Exam")
    public FullExamDto findById(@PathVariable("id") int id) {
        return examService.findById(id);
    }


    @PostMapping
    @Operation(summary = "Create a new exam.", tags = "Exam")
    public FullExamDto createExam(@RequestBody ExamRequest request) {
        return examService.createExam(request);
    }


    @GetMapping("/{id}/export")
    @Operation(
            summary = "Get template for an exam.",
            description = "Retrieves a template for an exam by his ID and the specified document name(type): \"with_answer\" or \"without_answer\".",
            tags = "Exam")
    public ModelAndView exportDocument(
            @PathVariable("id") int examId,
            @RequestParam("type") String type) {
        FullExamDto exam;
        if ("with_answer".equals(type)) {
            exam = examService.findByIdWithAnswers(examId);
        } else {
            exam = examService.findById(examId);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(type);
        modelAndView.addObject("exam", exam);
        return modelAndView;
    }
}
