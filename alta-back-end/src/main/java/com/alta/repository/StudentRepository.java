package com.alta.repository;

import com.alta.entity.Student;
import com.alta.entity.TasksGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Student} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
