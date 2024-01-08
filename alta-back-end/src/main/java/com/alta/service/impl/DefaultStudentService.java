package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.exception.StudentException;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentException(studentId));
    }

    @Override
    @Transactional
    public void assigneTasks(int id, List<Integer> tasks) {
        studentRepository.updateTasksIds(id, tasks.toString());
    }
}
