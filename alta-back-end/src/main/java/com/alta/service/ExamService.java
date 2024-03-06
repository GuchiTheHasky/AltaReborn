package com.alta.service;

import com.alta.dto.ExamDto;
import com.alta.dto.FullExamDto;
import com.alta.entity.Exam;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.web.entity.ExamRequest;
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
     * @return The found {@link FullExamDto}, or null if not found.
     */
    FullExamDto findById(int examId);

        // to do -> see in DefaultExamService
//    /**
//     * Finds task groups associated with a list of student IDs.
//     *
//     * @param studentsIds A list of student IDs to find task groups for.
//     * @return A list of {@link ExamDto} associated with the given student IDs.
//     */
//    List<ExamDto> findByStudentIds(List<Integer> studentsIds);


    FullExamDto createExam(ExamRequest request);

    List<ExamDto> findAll();

    List<ExamDto> findAllExamsPageByPage(PageRequest pageRequest);
}
