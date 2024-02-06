package com.alta.web.entity;

import com.alta.dto.StudentDto;
import com.alta.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksRequest {
    private List<TopicDto> topics;
    private List<StudentDto> students;
}
