package com.alta.facade;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;

import java.util.List;

public interface MainFacade {
//    /**
//     * Fetches a list of unfinished tasks based on Students IDs and IDs of Topic objects.
//     * @param studentsIds : The List of Student objects IDs on the basis of which tasks should be selected.
//     * @param topicsIds : List of Topic objects IDs on the basis of which tasks should be selected.
//     * @return List of TaskDto objects representing the tasks that match the information provided.
//     */
    List<TaskDto> findUnfinishedTasks(List<Integer> topicsIds, List<Integer> studentsIds);


    //List<TaskDto> findUnfinishedTasks(List<TopicDto> topics, List<StudentDto> students);


    /**
     * Updates the list of tasks of Student object and retrieve list of tasks that match ids specified.
     * @param studentsIds : List of the IDs of the Student objects which tasks should be updated.
     * @param tasksIds : List of tasks ids that should be set.
     */
    List<TaskDto> updateStudentTasksAndRetrieveDto(List<Integer> studentsIds, List<Integer> tasksIds);

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
     * Returns a list of all Topic objects available.
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAllTopics();

    List<Student> findAllStudentsById(List<Integer> studentsIds);
}
