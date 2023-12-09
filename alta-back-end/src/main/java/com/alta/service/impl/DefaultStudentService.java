package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.exception.StudentException;
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
    private final StudentRepository STUDENT_REPOSITORY;
    private final StudentMapper STUDENT_Mapper;

    @Override
    public List<StudentDto> findAll() {
        return STUDENT_REPOSITORY.findAll().stream().map(STUDENT_Mapper::toStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student newStudent = STUDENT_Mapper.toStudent(studentDto);
        return STUDENT_Mapper.toStudentDto(STUDENT_REPOSITORY.save(newStudent));
    }

    @Override
    public void delete(int id) {
        STUDENT_REPOSITORY.findById(id).ifPresent(student -> STUDENT_REPOSITORY.deleteById(id));
    }

    @Override
    public StudentDto update(int id, StudentDto studentDto) {
        return STUDENT_REPOSITORY.findById(id)
                .map(studentRequired -> {
                    studentRequired.setFirstName(studentDto.getFirstName());
                    studentRequired.setLastName(studentDto.getLastName());
                    studentRequired.setEmail(studentDto.getEmail());
                    studentRequired.setGrade(studentDto.getGrade());
                    studentRequired.setStatus(studentDto.getStatus());
                    return STUDENT_Mapper.toStudentDto(STUDENT_REPOSITORY.save(studentRequired));
                })
                .orElseThrow(() -> new StudentException(id));
    }

    @Override
    public Student findById(int studentId) {
        return STUDENT_REPOSITORY.findById(studentId).orElseThrow(() -> new StudentException(studentId));
    }
}
