package com.alta.service;

import com.alta.dto.StudentDto;
import java.util.List;

/**
 * Interface for managing the Student data.
 * Provides functionality to return, save, update, and delete Student objects.
 */

public interface StudentService {

    /**
     * Returns a list of all Student objects available.
     *
     * @return A list of StudentDto objects representing the available students.
     */
    List<StudentDto> findAll();

    /**
     * Saves a new Student.
     *
     * @param studentDto : An object containing information about the student to be saved.
     * @return An object representing the newly saved student.
     */
    StudentDto save(StudentDto studentDto);

    /**
     * Deletes a Student object based on its unique identifier.
     *
     * @param id : The unique identifier of the Student object to be deleted.
     */
    void delete(int id);

    /**
     * Updates an existing Student object with the provided information.
     *
     * @param id : The unique identifier of the Student object to be updated.
     * @param studentDto : An object containing the updated information for the student.
     * @return An object representing the updated student.
     */
    StudentDto update(int id, StudentDto studentDto);
}
