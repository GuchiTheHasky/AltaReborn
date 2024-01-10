package com.alta.service;

import com.alta.dto.TaskDto;

import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, update, and filter Task objects.
 */

public interface TaskService {
    /**
     * Fetches a list of tasks based on Topic IDs, excluding Task objects of provided IDs.
     * @param topicsIds : The list of IDs of the Topic objects on the basis of which tasks should be selected.
     * @param tasksIds : The list of IDs of the Task objects to be excluded.
     * @return List of TaskDto objects representing the tasks filtered according to the principle stated above.
     */
    List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicsIds, List<Integer> tasksIds);

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
}
