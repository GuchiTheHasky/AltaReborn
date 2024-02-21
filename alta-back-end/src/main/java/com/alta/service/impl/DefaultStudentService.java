package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).toList();
    }

    @Override
    public List<StudentDto> findAllByIds(List<Integer> studentIds) {
        return studentRepository.findAllById(studentIds).stream().map(studentMapper::toStudentDto).toList();
    }
}
