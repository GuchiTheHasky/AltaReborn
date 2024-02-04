package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.facade.MainFacade;
import com.alta.mapper.StudentMapper;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.TopicMapper;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultMainFacade implements MainFacade {
    private final TopicService topicService;
    private final TaskService taskService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> findUnfinishedTasks(List<TopicDto> topics, List<StudentDto> studentsDto) {
        List<Student> students = studentMapper.toStudentList(studentsDto);
        return filterOfUnfinishedTasks(topics, students);
    }

    @Override
    @Transactional
    public Map<String, List<TaskDto>> updateStudentTasksAndRetrieveDto(List<StudentDto> studentsDto, List<TaskDto> tasksDto) { // todo rename method
        List<Student> students = studentMapper.toStudentList(studentsDto);
        List<Task> tasks = taskMapper.toTaskList(tasksDto);
        return assignTasks(students, tasks);
    }

    @Override
    public List<TopicDto> findAllTopics() {
        return topicService.findAll();
    }

    @Override
    public List<TaskDto> findTasksCompletedByAtLeastOneStudent(List<TopicDto> topics, List<StudentDto> studentsDto) {
        List<Student> students = studentMapper.toStudentList(studentsDto);

        return filterOfTasksCompletedByAtLeastOneStudent(topics, students);
    }

    List<TaskDto> filterOfTasksCompletedByAtLeastOneStudent(List<TopicDto> topics, List<Student> students) {
        List<Task> completedTasks = studentService.getTasks(students);

        return taskService.getTasksCompletedByAtLeastOneStudent(topics, completedTasks);
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    List<TaskDto> filterOfUnfinishedTasks(List<TopicDto> topics, List<Student> students) {
        List<Task> completedTasks = studentService.getTasks(students);

        return taskService.getUnfinishedTasks(topics, completedTasks);
    }

    Map<String, List<TaskDto>> assignTasks(List<Student> students, List<Task> tasksToAdd) {
        return getMapOfStudentsAndTasksAssigned(students, tasksToAdd);
    }

    private Map<String, List<TaskDto>> getMapOfStudentsAndTasksAssigned(List<Student> students, List<Task> tasksToAdd) {
        Map<String, List<TaskDto>> mapOfStudentsAndTasksAssigned = new HashMap<>();

        students.forEach(student -> {
            List<Task> newTasks = taskService.excludeCompletedTasks(tasksToAdd, student);

            student.getTasks().addAll(tasksToAdd);
            studentService.save(student);

            mapOfStudentsAndTasksAssigned.put(studentMapper.toStudentDto(student).getFullName(),
                    taskMapper.toTaskDtoList(newTasks));
        });

        return mapOfStudentsAndTasksAssigned;
    }
}
