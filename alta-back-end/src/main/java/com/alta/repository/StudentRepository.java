package com.alta.repository;

import com.alta.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query(value = "UPDATE students SET tasks_ids = tasks_ids || CAST(:taskId AS jsonb) WHERE id = :studentId", nativeQuery = true)
    void updateTasksIds(@Param("studentId") int studentId, @Param("taskId") String taskId);


}
