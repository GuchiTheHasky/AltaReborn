package com.alta.mapper;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "fullName", expression = "java(student.getLastName() + ' ' + student.getFirstName())")
    StudentDto toStudentDto(Student student);

    @Mapping(target = "lastName", expression = "java(studentDto.getFullName().split(\" \")[0])")
    @Mapping(target = "firstName", expression = "java(studentDto.getFullName().split(\" \")[1])")
    Student toStudent(StudentDto studentDto);

    List<Student> toStudentList(List<StudentDto> studentsDto);
}
