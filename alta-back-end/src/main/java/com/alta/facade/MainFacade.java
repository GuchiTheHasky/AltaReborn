package com.alta.facade;

import com.alta.dto.TaskDto;

import java.util.List;

public interface MainFacade { // todo: add documentation.
    List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, Integer studentId);

    List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds);
}
