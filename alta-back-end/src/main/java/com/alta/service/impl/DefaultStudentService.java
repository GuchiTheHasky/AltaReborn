package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.service.StudentService;
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
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Task> getTasks(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getTasks().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
