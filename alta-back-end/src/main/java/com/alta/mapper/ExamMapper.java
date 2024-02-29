package com.alta.mapper;

import com.alta.dto.ExamDto;
import com.alta.entity.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, StudentMapper.class})
public interface ExamMapper {

    ExamDto toExamDto(Exam exam);

}
