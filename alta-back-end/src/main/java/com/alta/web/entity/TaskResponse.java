package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.entity.TasksGroup;

public record TaskResponse(StudentDto student, TasksGroup group) {
}
