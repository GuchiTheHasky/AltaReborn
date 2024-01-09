package com.alta.facade;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;

import java.util.List;

public interface MainFacade { // todo: add documentation.
    List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, Integer studentId);

    List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds);

    List<StudentDto> findAllStudents();

    TaskDto updateTask(int id, TaskDto taskDto);

    List<TopicDto> findAllTopics();
}
