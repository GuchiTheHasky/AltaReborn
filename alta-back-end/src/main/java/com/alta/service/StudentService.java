package com.alta.service;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * StudentService interface for managing {@link com.alta.entity.Student} entities.
 */
public interface StudentService {

    List<StudentDto> findAll(Integer page, Integer size);

    /**
     * Retrieves students by their IDs.
     *
     * @param studentIds A list of student IDs to find matching students.
     * @return A list of {@link StudentDto} representing the students with the specified IDs.
     */
    List<Student> findAllByIds(List<Integer> studentIds);

}
