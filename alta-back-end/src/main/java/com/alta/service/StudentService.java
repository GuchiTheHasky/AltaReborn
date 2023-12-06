package com.alta.service;

import com.alta.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();

    StudentDto save(StudentDto studentDto);

    void delete(int id);

    StudentDto update(int id, StudentDto studentDto);
}
