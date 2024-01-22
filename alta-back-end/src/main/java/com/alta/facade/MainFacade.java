package com.alta.facade;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;

import java.util.List;

public interface MainFacade {
    /**
     * Fetches a list of unfinished tasks based on Student ID and IDs of Topic objects.
     * @param studentId : The ID of Student object on the basis of which tasks should be selected.
     * @param topicsIds : List of Topic objects IDs on the basis of which tasks should be selected.
     * @return List of TaskDto objects representing the tasks that match the information provided.
     */
    List<TaskDto> findUnfinishedTasks(List<Integer> topicsIds, Integer studentId);

    /**
     * Updates the list of tasks of Student object and retrieve list of tasks that match ids specified.
     * @param studentId : The unique identifier of the Student object which tasks should be updated.
     * @param tasksIds : List of tasks ids that should be set.
     */
    List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> tasksIds);

    /**
     * Returns a list of all Student objects available.
     * @return A list of StudentDto objects representing the available students.
     */
    List<StudentDto> findAllStudents();

    /**
     * Updates an existing Task object with the provided information.
     * @param taskDto : An object containing the updated information for the task.
     * @return An object representing the updated task.
     */
    TaskDto updateTask(TaskDto taskDto);

    /**
     * Fetches a list of unfinished tasks based on Student ID and IDs of Topic objects.
     * @param studentId : The ID of Student object on the basis of which tasks should be selected.
     * @param topicsIds : List of Topic objects IDs on the basis of which tasks should be selected.
     * @return List of TaskDto objects representing the tasks filtered according to the principle stated above.
     */
    List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicsIds, Integer studentId);

}
