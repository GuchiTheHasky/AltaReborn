package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.facade.MainFacade;
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
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get all students with optional pagination.",
            tags = "Student")
    public List<StudentDto> findAll(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) Integer size) {

        return Optional.ofNullable(page)
                .flatMap(p -> Optional.ofNullable(size)
                        .map(s -> PageRequest.of(page, size)))
                .map(mainFacade::findAllStudentsPageByPage)
                .orElseGet(mainFacade::findAllStudents);
    }

}
