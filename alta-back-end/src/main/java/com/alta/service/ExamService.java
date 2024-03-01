package com.alta.service;

import com.alta.dto.ExamDto;
import com.alta.entity.Exam;
import com.alta.entity.Student;
import com.alta.entity.Task;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service interface for managing {@link Exam} entities.
 */
public interface ExamService {

    /**
     * Finds an exam by its ID.
     *
     * @param examId The ID of the task group to find.
     * @return The found {@link ExamDto}, or null if not found.
     */
    ExamDto findById(int examId);

        // to do -> see in DefaultExamService
//    /**
//     * Finds task groups associated with a list of student IDs.
//     *
//     * @param studentsIds A list of student IDs to find task groups for.
//     * @return A list of {@link ExamDto} associated with the given student IDs.
//     */
//    List<ExamDto> findByStudentIds(List<Integer> studentsIds);

    /**
     * Creates a new task group for a student with a set of tasks.
     *
     * @param students The list of {@link Student} for whom to create the exam.
     * @param tasks A list of {@link Task} to include in the task group.
     * @return The newly created {@link Exam}.
     */
    ExamDto createExam(String title, List<Student> students, List<Task> tasks);

    List<ExamDto> findAll();

    List<ExamDto> findAllExamsPageByPage(PageRequest pageRequest);
}
