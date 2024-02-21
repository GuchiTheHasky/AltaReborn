package com.alta.repository;

import com.alta.entity.TasksGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing {@link TasksGroup} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
public interface TaskGroupRepository extends JpaRepository<TasksGroup, Integer> {

    /**
     * Finds task groups by a list of student IDs.
     *
     * @param studentsIds The list of student IDs to find task groups for.
     * @return A list of {@link TasksGroup} associated with the given student IDs.
     */
    @Query("select tg from TasksGroup tg where tg.studentId in :studentsIds")
    List<TasksGroup> findByStudentIds(@Param("studentsIds") List<Integer> studentsIds);

    /**
     * Finds task groups for a specific student ID.
     *
     * @param studentId The student ID to find task groups for.
     * @return A list of {@link TasksGroup} associated with the given student ID.
     */
    @Query("select tg from TasksGroup tg where tg.studentId in :studentId")
    List<TasksGroup> findByStudentId(@Param("studentId") int studentId);

}
