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
}
