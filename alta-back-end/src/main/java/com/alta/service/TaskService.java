package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;

import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, save, update, and delete Task objects.
 */

public interface TaskService {  // todo: check documentation.
    List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicIds, List<Integer> tasksIds);


    /**
     * Returns a list of all Tasks objects available.
     * @return A list of TaskDto objects representing the available tasks.
     */
    List<TaskDto> findAll();


    /**
     * Saves a new Task.
     * @param taskDto : An object containing information about the task to be saved.
     * @return An object representing the newly saved task.
     */
    TaskDto save(TaskDto taskDto);


    /**
     * Updates an existing Task object with the provided information.
     * @param id : The unique identifier of the Task object to be updated.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);

    List<TaskDto> findAllByIds(List<Integer> taskIds);

}
