package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;

import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, update, and filter Task objects.
 */

public interface TaskService {

    /**
     * Updates an existing Task object with the provided information.
     * @param id : The unique identifier of the Task object to be updated.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);

    /**
     * Fetches a list of tasks based on their IDs.
     * @param tasksIds : The list of unique identifiers of the Task objects to be found.
     * @return List of TaskDto objects representing the tasks that match the provided IDs.
     */
    List<TaskDto> findAllByIds(List<Integer> tasksIds);

    /**
     * Gets a Task object based on its unique identifier.
     * @param taskId : The unique identifier of the Task object to be found.
     */
    TaskDto findById(Integer taskId);

    Task findTaskById(int id);
}
