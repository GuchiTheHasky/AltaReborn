package com.alta.service;

import com.alta.entity.Task;
import com.alta.entity.TasksGroup;

import java.util.List;

/**
 * Service interface for managing {@link TasksGroup} entities.
 */
public interface TaskGroupService {

    /**
     * Finds a task group by its ID.
     *
     * @param taskGroupId The ID of the task group to find.
     * @return The found {@link TasksGroup}, or null if not found.
     */
    TasksGroup findById(int taskGroupId);

    /**
     * Finds task groups associated with a list of student IDs.
     *
     * @param studentsIds A list of student IDs to find task groups for.
     * @return A list of {@link TasksGroup} associated with the given student IDs.
     */
    List<TasksGroup> findByStudentIds(List<Integer> studentsIds);

    /**
     * Saves a task group.
     *
     * @param group The {@link TasksGroup} to save.
     * @return The saved {@link TasksGroup}.
     */
    TasksGroup save(TasksGroup group);

    /**
     * Creates a new task group for a student with a set of tasks.
     *
     * @param studentId The ID of the student for whom to create the task group.
     * @param tasks A list of {@link Task} to include in the task group.
     * @return The newly created {@link TasksGroup}.
     */
    TasksGroup createTasksGroup(int studentId, List<Task> tasks);
}
