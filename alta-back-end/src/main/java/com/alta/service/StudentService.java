package com.alta.service;

import com.alta.dto.StudentDto;

import java.util.List;

/**
 * StudentService interface for managing {@link com.alta.entity.Student} entities.
 */
public interface StudentService {

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
    List<StudentDto> findAllByIds(List<Integer> studentIds);
}
