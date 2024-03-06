package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @Operation(
            summary = "Get all students",
            description = "Get all students with optional pagination.",
            tags = "Student")
    public List<StudentDto> findAll(
            @RequestParam(required = false) @Min(0) Integer page,
            @RequestParam(required = false) @Min(1) Integer size) {

        return Optional.ofNullable(page)
                .flatMap(p -> Optional.ofNullable(size)
                        .map(s -> PageRequest.of(page, size)))
                .map(studentService::findAllStudentsPageByPage)
                .orElseGet(studentService::findAll);
    }

}
