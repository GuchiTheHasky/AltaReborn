package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.exception.StudentException;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto findById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentException(id);
        }
        return studentMapper.toStudentDto(student.get());
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentDto)
                .sorted(Comparator.comparing(StudentDto::getFullName))
                .toList();
    }

    @Override
    public List<StudentDto> findAllByIds(List<Integer> studentIds) {
        return studentRepository.findAllById(studentIds).stream()
                .map(studentMapper::toStudentDto)
                .toList();
    }

    @Override
    public List<StudentDto> findAllStudentsPageByPage(PageRequest pageRequest) {
        Page<Student> studentsPage = studentRepository.findAll(pageRequest);

        return studentsPage.getContent().stream()
                .map(studentMapper::toStudentDto)
                .sorted(Comparator.comparing(StudentDto::getFullName))
                .toList();
    }
}
