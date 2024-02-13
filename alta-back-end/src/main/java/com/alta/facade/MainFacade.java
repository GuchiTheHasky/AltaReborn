package com.alta.facade;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

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


    /**
     * Finds tasks that are unfinished within given topics and student data.
     *
//     * @param topics      List of topics to search for unfinished tasks.
     * @param studentsDto List of student data to search for unfinished tasks.
     * @return A list of unfinished tasks.
     */
   // List<TaskDto> findTasksUnfinishedForAllStudents(List<TopicDto> topics, List<StudentDto> studentsDto);

    Page<TaskDto> findTasksUnfinishedForAllStudents(List<TopicDto> topicsDto, List<StudentDto> studentsDto, PageRequest pageRequest);


                                                    /**
     * Finds tasks completed by at least one student within given topics and student data.
     *
     * @param topics      List of topics to search for completed tasks.
     * @param studentsDto List of student data to search for completed tasks.
     * @return A list of completed tasks.
     */
    List<TaskDto> findTasksCompletedByAtLeastOneStudent(List<TopicDto> topics, List<StudentDto> studentsDto);


    /**
     * Updates the tasks of specified students and retrieves the assigned tasks matching given students' full names.
     *
     * @param studentsDto List of students which tasks should be updated.
     * @param tasks       List of task data to set for the students.
     * @return A map where keys are students' full names and values are lists of tasks assigned.
     */
    Map<String, List<TaskDto>> updateStudentTasksAndRetrieveDto(List<StudentDto> studentsDto, List<TaskDto> tasks);


    /**
     * Updates an existing task with the provided information.
     *
     * @param id      The ID of the task to update.
     * @param taskDto An object containing the updated task information.
     * @return An object representing the updated task.
     */
    TaskDto updateTask(int id, TaskDto taskDto);
}
