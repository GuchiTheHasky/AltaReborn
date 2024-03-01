package com.alta.service;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * StudentService interface for managing {@link com.alta.entity.Student} entities.
 */
public interface StudentService {

    StudentDto findById(int id);

    /**
     * Retrieves all students.
     *
     * @return A list of {@link StudentDto} representing all students.
     */
    List<StudentDto> findAll();

    /**
     * Retrieves students by their IDs.
     *
     * @param studentIds A list of student IDs to find matching students.
     * @return A list of {@link StudentDto} representing the students with the specified IDs.
     */
    List<Student> findAllByIds(List<Integer> studentIds);

    List<StudentDto> findAllStudentsPageByPage(PageRequest pageRequest);
}
