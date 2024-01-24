package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;

import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, update, and filter Task objects.
 */

public interface TaskService {

    /**
     * Updates an existing Task object with the provided information.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(TaskDto taskDto);

    /**
     * Fetches a list of tasks based on their IDs.
     * @param tasksIds : The list of unique identifiers of the Task objects to be found.
     * @return List of TaskDto objects representing the tasks that match the provided IDs.
     */
    List<TaskDto> findAllByIds(List<Integer> tasksIds);

   // List<TaskDto> getUnfinishedTasks(List<Integer> selectedTopicsIdList, List<Task> completedTasks);

    List<TaskDto> getUnfinishedTasks(List<Topic> selectedTopicsIdList, List<Task> completedTasks);

    List<Task> findAllById(List<Integer> tasks);
}
