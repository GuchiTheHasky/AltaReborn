package com.alta.mapper;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    //@Mapping(target = "email", ignore = true) // todo: check it, I supposed that it is not necessary
    //@Mapping(target = "tasks", ignore = true) // todo: check it, I supposed that it is not necessary
    Student toStudent(StudentDto studentDto);

    StudentDto toStudentDto(Student student);
}
