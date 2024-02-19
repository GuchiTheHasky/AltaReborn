package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.dto.TasksGroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksAssignmentResponse {
    StudentDto studentDto;
    TasksGroupDto tasksGroupDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksAssignmentResponse that = (TasksAssignmentResponse) o;
        return Objects.equals(studentDto, that.studentDto) &&
                Objects.equals(tasksGroupDto, that.tasksGroupDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentDto, tasksGroupDto);
    }
}
