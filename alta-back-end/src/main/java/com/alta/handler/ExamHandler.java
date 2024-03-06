package com.alta.handler;

import com.alta.dto.FullExamDto;
import com.alta.web.entity.ExamRequest;

public interface ExamHandler {

    FullExamDto createExam(ExamRequest request);
}
