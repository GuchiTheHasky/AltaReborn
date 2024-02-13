package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;

import java.util.List;
import java.util.Set;

/**
 * Interface for managing Task data.
 * Provides functionality to update, filter, and retrieve Task objects.
 */

public interface TaskService {

    /**
     * Retrieves unfinished tasks based on selected topics and completed tasks.
     * @param selectedTopicsList A list of TopicDto objects representing selected topics.
     * @param completedTasks A set of completed Task objects.
     * @return A list of TaskDto objects representing unfinished tasks.
     */
    List<TaskDto> getUnfinishedTasks(List<TopicDto> selectedTopicsList, Set<Task> completedTasks);


    /**
     * Retrieves tasks completed by at least one student within specified topics.
     * @param topics A list of TopicDto objects representing specified topics.
     * @param completedTasks A set of completed Task objects.
     * @return A list of TaskDto objects representing tasks completed by at least one student within specified topics.
     */
    List<TaskDto> getTasksCompletedByAtLeastOneStudent(List<TopicDto> topics, Set<Task> completedTasks);


    /**
     * Excludes completed tasks based on a student's completed tasks.
     * @param tasksToAdd A list of Task objects to add.
     * @param student A Student object representing a student.
     * @return A list of Task objects excluding tasks completed by the student.
     */
    List<Task> excludeCompletedTasks(List<Task> tasksToAdd, Student student);

    /**
     * Updates an existing Task object with the provided information.
     *
     * @param id The ID of the task to update.
     * @param taskDto An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto update(int id, TaskDto taskDto);
}
