package com.alta.service;

import com.alta.dto.TaskDto;

import java.util.List;

public interface TaskFilter { // todo: add documentation.
    List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicIds, Integer studentId);
}
