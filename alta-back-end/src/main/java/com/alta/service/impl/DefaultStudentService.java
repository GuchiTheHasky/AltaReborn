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
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student newStudent = studentMapper.toStudent(studentDto);
        return studentMapper.toStudentDto(studentRepository.save(newStudent));
    }

    @Override
    public void delete(int id) {
        studentRepository.findById(id).ifPresent(student -> studentRepository.deleteById(id));
    }

    @Override
    public StudentDto update(int id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .map(studentRequired -> {
                    studentRequired.setFirstName(studentDto.getFirstName());
                    studentRequired.setLastName(studentDto.getLastName());
                    studentRequired.setEmail(studentDto.getEmail());
                    studentRequired.setGrade(studentDto.getGrade());
                    studentRequired.setStatus(studentDto.getStatus());
                    return studentMapper.toStudentDto(studentRepository.save(studentRequired));
                })
                .orElseThrow(() -> new StudentException(id));
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentException(studentId));
    }

    @Override
    public Student findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }
}
