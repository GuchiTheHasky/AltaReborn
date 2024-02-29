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

    /**
     * Finds task groups by a list of student IDs.
     *
     * @param studentsIds The list of student IDs to find task groups for.
     * @return A list of {@link Exam} associated with the given student IDs.
     */
    @Query("select tg from TasksGroup tg where tg.studentId in :studentsIds")
    List<Exam> findByStudentIds(@Param("studentsIds") List<Integer> studentsIds);

//    /**
//     * Finds task groups for a specific student ID.
//     *
//     * @param studentId The student ID to find task groups for.
//     * @return A list of {@link Exam} associated with the given student ID.
//     */
//    @Query("select tg from TasksGroup tg where tg.studentId in :studentId")
//    List<Exam> findByStudentId(@Param("studentId") int studentId);

}
