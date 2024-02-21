package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;

import java.util.List;

/**
 * Service interface for managing {@link Task} entities.
 */
public interface TaskService {

    /**
     * Finds tasks by their IDs.
     *
     * @param tasksIds A list of task IDs to find matching tasks.
     * @return A list of {@link Task} representing the tasks with the specified IDs.
     */
    List<Task> findAllByIds(List<Integer> tasksIds);

    /**
     * Updates an existing Task object with the provided information.
     *
     * @param id      The ID of the task to update.
     * @param taskDto An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);

    /**
     * Finds tasks associated with a list of topic IDs.
     *
     * @param topicsIds A list of topic IDs to find tasks related to those topics.
     * @return A list of {@link TaskDto} representing tasks associated with the specified topic IDs.
     */
    List<TaskDto> findByTopicIds(List<Integer> topicsIds);
}
