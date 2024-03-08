package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll(Integer page, Integer size) {
        return Optional.ofNullable(page).isEmpty() || Optional.ofNullable(size).isEmpty() ? findAllStudents() : findAllStudents(page, size);
    }

    @Override
    public List<Student> findAllByIds(List<Integer> studentIds) {
        return studentIds == null || studentIds.isEmpty() ? Collections.emptyList() : studentRepository.findAllById(studentIds);
    }

    private List<StudentDto> findAllStudents(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentsPage = studentRepository.findAll(pageable);
        return studentsPage.stream()
                .map(studentMapper::toStudentDto)
                .toList();
    }

    private List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toStudentDto)
                .toList();
    }
}
