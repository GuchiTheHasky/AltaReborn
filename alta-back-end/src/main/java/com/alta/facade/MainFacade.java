package com.alta.facade;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.TasksGroup;
import com.alta.web.entity.TaskResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * MainFacade serves as an interface for managing tasks, students, and topics.
 * It provides methods to update student tasks, retrieve student and topic data, and find tasks based on various criteria.
 */

public interface MainFacade {

    /**
     * Retrieves a list of all available students.
     *
     * @return A list of StudentDto objects representing the available students.
     */
    List<StudentDto> findAllStudents();


    /**
     * Retrieves a list of all available topics.
     *
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAllTopics();

    TasksGroup findTaskGroupById(int id);

    List<TasksGroup> findTasksGroupByStudentIds(List<Integer> studentsIds);


    List<TaskDto> findAllTasks(List<Integer> studentIds, List<Integer> topicIds);

    List<TaskResponse> receiveAssignmentTasks(List<Integer> studentsIds, List<Integer> tasksIds);

    /**
     * Updates an existing task with the provided information.
     *
     * @param id      The ID of the task to update.
     * @param taskDto An object containing the updated task information.
     * @return An object representing the updated task.
     */
    TaskDto updateTask(int id, TaskDto taskDto);

    List<TopicDto> findAllTopicsPageByPage(PageRequest pageRequest);
}
