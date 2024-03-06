package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;

import java.util.List;

public record ExamRequest(String name, List<StudentDto> students, List<TaskDto> tasks) {
}
