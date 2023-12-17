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

}
