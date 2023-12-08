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
    private final StudentService STUDENT_SERVICE;

    @GetMapping
    public List<StudentDto> findAll() {
        return STUDENT_SERVICE.findAll();
    }

    @PostMapping("/save")
    public StudentDto save(@RequestBody StudentDto studentDto) {
        return STUDENT_SERVICE.save(studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        STUDENT_SERVICE.delete(id);
    }

    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable("id") int id, @RequestBody StudentDto studentDto) {
        return STUDENT_SERVICE.update(id, studentDto);
    }
}
