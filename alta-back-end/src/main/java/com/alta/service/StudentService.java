package com.alta.service;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.entity.Task;

import java.util.List;
import java.util.Set;

/**
 * Interface for managing Student data.
 * Provides functionality to retrieve and update Student objects.
 */

public interface StudentService {

    /**
     * Retrieves a list of all available Student objects.
     * @return A list of StudentDto objects representing the available students.
     */
    List<StudentDto> findAll();


    /**
     * Retrieves tasks completed by specified list of students.
     * @param students A list of Student objects.
     * @return A list of Task objects completed by specified students.
     */
    Set<Task> getTasks(List<Student> students);


    /**
     * Saves a new Student object or updates an existing one.
     * @param student The Student object to save or update.
     */
    void save(Student student);
}
