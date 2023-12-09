package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();
    TaskDto save(TaskDto taskDto);
    void delete(int id);

    TaskDto update(int id, TaskDto task);

    Task findById(int taskId);

    TaskDto addStudentToTask(int taskId, int studentId);

    TaskDto assignTopicToTask(int taskId, int topicId);

    List<TaskDto> getTasksByTopicId(int topicId);

    List<TaskDto> getTasksByStudentId(int studentId);
}
