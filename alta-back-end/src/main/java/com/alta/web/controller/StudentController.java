package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.mapper.StudentMapper;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;
    private final TaskService taskService;
    private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @PostMapping("/save")
    public StudentDto save(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

}
