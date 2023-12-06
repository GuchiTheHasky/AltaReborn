package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.service.StudentService;
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

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @PostMapping("/save")
    public StudentDto save(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        studentService.delete(id);
    }

    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable("id") int id, @RequestBody StudentDto studentDto) {
        return studentService.update(id, studentDto);
    }
}
