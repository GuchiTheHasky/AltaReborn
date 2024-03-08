package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.alta.web.util.PageableValidator.pageableValidation;

@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Get all students", tags = "Student")
    public List<StudentDto> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        pageableValidation(page, size);

        return studentService.findAll(page, size);
    }
}
