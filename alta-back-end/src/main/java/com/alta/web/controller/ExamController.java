package com.alta.web.controller;

import com.alta.dto.ExamDto;
import com.alta.handler.ExamHandler;
import com.alta.dto.FullExamDto;
import com.alta.service.ExamService;
import com.alta.web.entity.ExamRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping
    @Operation(
            summary = "Get all exams",
            description = "Get all exams with optional pagination.",
            tags = "Exam")
    public List<ExamDto> findAll(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) Integer size) {

            return examService.findAll();

//        return Optional.ofNullable(page)
//                .flatMap(p -> Optional.ofNullable(size)
//                        .map(s -> PageRequest.of(page, size)))
//                .map(examService::findAllExamsPageByPage)
//                .orElseGet(examService::findAll);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Find an exam by ID.",
            tags = "Exam")
    public FullExamDto findById(@PathVariable("id") int id) {
        return examService.findById(id);
    }


    @PostMapping
    @Operation(
            summary = "Create a new exam.",
            tags = "Exam"
    )
    public FullExamDto createExam(@RequestBody ExamRequest request) {

        return examService.createExam(request);
    }


    @GetMapping("/{id}/export")
    @Operation(
            summary = "Get template for an exam.",
            description = "Retrieves a template for an exam by its ID and the specified document name(type): \"with_answer\" or \"without_answer\".",
            tags = "Exam")
    public ModelAndView getModelAndView(
            @PathVariable("id") int examId,
            @RequestParam("type") String type) {
        FullExamDto exam = examService.findById(examId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(type);
        modelAndView.addObject("exam", exam);
        return modelAndView;
    }
}
