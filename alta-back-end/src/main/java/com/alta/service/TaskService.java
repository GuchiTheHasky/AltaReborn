package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import java.util.List;

/**
 * Interface for managing the Task data.
 * Provides functionality to return, save, update, and delete Task objects.
 */

public interface TaskService {
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
     * Deletes a Task object based on its unique identifier.
     * @param id : The unique identifier of the Task object to be deleted.
     */
    void delete(int id);


    /**
     * Updates an existing Task object with the provided information.
     * @param id : The unique identifier of the Task object to be updated.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);


    /**
     * Assigns a Student (identified by studentId) to a Task (identified by taskId)
     * @return An object representing the updated task.
     */
    TaskDto assignStudentToTask(int taskId, int studentId);


    /**
     * Sets Topic object (identified by topicId) to Task object (identified by taskId).
     * @return An object representing the updated task.
     */
    TaskDto assignTopicToTask(int taskId, int topicId);


    /**
     * Returns a list of TaskDto objects related to a specific Topic object (identified by topicId).
     * @return A list of TaskDto objects representing the tasks found.
     */
    List<TaskDto> findAllByTopicId(int topicId);


    /**
     * Returns a list of TaskDto objects related to a specific Student object (identified by studentId).
     * @return A list of TaskDto objects representing the tasks found.
     */
    List<TaskDto> findAllByStudentId(int studentId);


    /**
     * Returns a list of TaskDto objects related to a list of Topic objects (identified by topicIdList).
     * @return A list of TaskDto objects representing the tasks found.
     */
    List<TaskDto> findAllByTopicIdList(List<Integer> topicIdList);


    /**
     * Deletes a Student object (identified by studentId), removing its association with tasks.
     */
    void deleteStudentFromTasks(Student student);
}
