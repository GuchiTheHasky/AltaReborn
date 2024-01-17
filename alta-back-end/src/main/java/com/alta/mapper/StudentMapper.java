package com.alta.mapper;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "tasksIds", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Student toStudent(StudentDto studentDto);

    StudentDto toStudentDto(Student student);
}
