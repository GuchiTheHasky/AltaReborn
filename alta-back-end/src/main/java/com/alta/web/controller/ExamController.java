package com.alta.web.controller;

import com.alta.dto.ExamDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.ExamRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get all exams with optional pagination.",
            tags = "Exam")
    public List<ExamDto> findAll(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) Integer size) {

        return Optional.ofNullable(page)
                .flatMap(p -> Optional.ofNullable(size)
                        .map(s -> PageRequest.of(page, size)))
                .map(mainFacade::findAllExamsPageByPage)
                .orElseGet(mainFacade::findAllExams);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Find an exam by ID.",
            tags = "Exam")
    public ExamDto findById(@PathVariable("id") int id) {
        return mainFacade.findExamById(id);
    }


    @PostMapping("exams")
    @Operation(
            summary = "Create a new exam.",
            tags = "Exam"
    )
    public ExamDto createExam(@RequestBody ExamRequest request) {

        return mainFacade.createExam(request);
    }


    @GetMapping("/{id}/export")
    @Operation(
            summary = "Get template for an exam.",
            description = "Retrieves a template for an exam by its ID and the specified document name(type): \"with_answer\" or \"without_answer\".",
            tags = "Exam")
    public ModelAndView getModelAndView(
            @PathVariable("id") int examId,
            @RequestParam("type") String type) {
        ExamDto exam = mainFacade.findExamById(examId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(type);
        modelAndView.addObject("exam", exam); // to do -> change templates
        return modelAndView;
    }
}
