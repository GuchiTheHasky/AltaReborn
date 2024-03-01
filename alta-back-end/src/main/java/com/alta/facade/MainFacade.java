package com.alta.facade;

import com.alta.dto.*;
import com.alta.web.entity.ExamRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * MainFacade serves as an interface for managing tasks, students, and topics.
 * It provides methods to update student tasks, retrieve student and topic data, and find tasks based on various criteria.
 */

public interface MainFacade {

    // Student

    /**
     * Retrieves a student by unique Id.
     * @param id The Id of student to receive.
     */
    StudentDto findStudentById(int id);

    /**
     * Retrieves a list of all available students.
     *
     * @return A list of StudentDto objects representing the available students.
     */
    List<StudentDto> findAllStudents();

    /**
     * Retrieves a paginated list of all available students.
     *
     * @param pageRequest A PageRequest object containing the pagination information.
     * @return A list of StudentDto objects representing the students on the requested page.
     */
    List<StudentDto> findAllStudentsPageByPage(PageRequest pageRequest);

    // Topic
    /**
     * Retrieves a list of all available topics.
     *
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAllTopics();

    /**
     * Retrieves a paginated list of all available topics.
     *
     * @param pageRequest A PageRequest object containing the pagination information.
     * @return A list of TopicDto objects representing the topics on the requested page.
     */
    List<TopicDto> findAllTopicsPageByPage(PageRequest pageRequest);

        // TaskGroup
    /**
     * Retrieves a task group by its ID.
     *
     * @param id The unique identifier of the task group.
     * @return A TasksGroup object representing the task group found by the specified ID.
     */
    ExamDto findExamById(int id);


    // to do -> see in defaultMainFacade
//    /**
//     * Retrieves a list of tasks based on specified student and topic IDs.
//     *
//     * @param studentIds A list of student IDs to filter the tasks.
//     * @param topicIds   A list of topic IDs to filter the tasks.
//     * @return A list of TaskDto objects representing the tasks that match the specified criteria.
//     */
//    List<TaskDto> findAllTasks(List<Integer> studentIds, List<Integer> topicIds);


    // to do -> delete it
//    /**
//     * Assigns specified tasks to students and returns the assignment details.
//     *
//     * @param studentsIds A list of student IDs to which the tasks will be assigned.
//     * @param tasksIds    A list of task IDs that will be assigned to the students.
//     * @return A list of TaskResponse objects representing the details of the task assignments.
//     */
//    List<TaskResponse> receiveAssignmentTasks(List<Integer> studentsIds, List<Integer> tasksIds);


    /**
     * Updates an existing task with the provided information.
     *
     * @param id      The ID of the task to update.
     * @param taskDto An object containing the updated task information.
     * @return An object representing the updated task.
     */
    TaskDto updateTask(int id, TaskDto taskDto);

    // to do -> see in defaultMainFacade
//    /**
//     * Retrieves a paginated list of tasks based on specified student and topic IDs.
//     *
//     * @param studentIds  A list of student IDs to filter the tasks.
//     * @param topicIds    A list of topic IDs to filter the tasks.
//     * @param pageRequest A PageRequest object containing the pagination information.
//     * @return A Page of TaskDto objects representing the tasks that match the specified criteria on the requested page.
//     */
//    Page<TaskDto> findAllTasksPageByPage(List<Integer> studentIds, List<Integer> topicIds, PageRequest pageRequest);

    List<ExamDto> findAllExams();

    List<ExamDto> findAllExamsPageByPage(PageRequest pageRequest);

    ExamDto createExam(ExamRequest request);

    List<ZnoDto> findAllZnos();

    List<ZnoDto> findAllZnosPageByPage(PageRequest pageRequest);

    ZnoDto findZnoById(int id);
}
