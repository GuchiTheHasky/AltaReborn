package com.alta.service;

import com.alta.dto.TaskDto;

import java.util.List;

public interface TaskFilter {
    /**
     * Fetches a list of unfinished tasks based on Student ID and IDs of Topic objects.
     * @param studentId : The ID of Student object on the basis of which tasks should be selected.
     * @param topicsIds : List of Topic objects IDs on the basis of which tasks should be selected.
     * @return List of TaskDto objects representing the tasks filtered according to the principle stated above.
     */
    List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicsIds, Integer studentId);

    /**
     * Fetches a list of Tasks IDs based on Student id excluding tasks which are already in Student tasks_ids list.
     * @param studentId : The ID of Student object on the basis of which tasks should be selected.
     * @param tasksIds : List of Task objects IDs on the basis of which tasks should be selected.
     * @return List of Tasks IDs representing the tasks filtered according to the principle stated above.
     */
    List<Integer> filterOfUniqueTasks(List<Integer> tasksIds, Integer studentId);
}
