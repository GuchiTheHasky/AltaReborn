package com.alta.repository;

import com.alta.entity.Student;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Student} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
@NonNullApi
public interface StudentRepository extends JpaRepository<Student, Integer> {
    /**
     * Retrieves a page of students.
     *
     * @param pageable Object defining the page to retrieve.
     * @return A page of students.
     */
    Page<Student> findAll(Pageable pageable);
}
