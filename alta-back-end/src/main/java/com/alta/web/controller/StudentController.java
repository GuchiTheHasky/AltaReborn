package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.facade.MainFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@RestController
public class StudentController {
    private final MainFacade mainFacade;

    @GetMapping
    public List<StudentDto> findAll() {
        return mainFacade.findAllStudents();
    }

}
