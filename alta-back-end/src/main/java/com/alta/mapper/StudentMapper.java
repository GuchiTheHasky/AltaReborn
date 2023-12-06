package com.alta.mapper;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentDto studentDto);

    StudentDto toStudentDto(Student student);
}
