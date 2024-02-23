package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.dto.TasksGroupDto;

public record TaskResponse(StudentDto student, TasksGroupDto group) {
}
