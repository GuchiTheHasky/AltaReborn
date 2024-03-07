package com.alta.service;

import com.alta.dto.ExamDto;
import com.alta.dto.FullExamDto;
import com.alta.entity.Exam;
import com.alta.web.entity.ExamRequest;

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

    FullExamDto createExam(ExamRequest request);

    List<ExamDto> findAll(Integer page, Integer size);

    FullExamDto findByIdWithAnswers(int examId);
}
