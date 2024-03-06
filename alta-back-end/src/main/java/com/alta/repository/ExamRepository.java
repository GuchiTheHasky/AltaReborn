package com.alta.repository;

import com.alta.entity.Exam;
import com.alta.entity.Student;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing {@link Exam} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
@NonNullApi
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    Page<Exam> findAll(Pageable pageable);

}
