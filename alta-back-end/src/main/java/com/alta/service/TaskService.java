package com.alta.service;

import com.alta.dto.TaskDto;
import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, save, update, and delete Task objects.
 */

public interface TaskService {
    /**
     * Returns a list of all Tasks objects available.
     *
     * @return A list of TaskDto objects representing the available tasks.
     */
    List<TaskDto> findAll();

    /**
     * Updates an existing Task object with the provided information.
     * @param id : The unique identifier of the Task object to be updated.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);

    TaskDto findById(int i);
}
