package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.dto.ExamDto;

public record TaskResponse(StudentDto student, ExamDto exam) {
}
