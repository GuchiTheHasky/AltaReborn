package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.facade.MainFacade;
import com.alta.mapper.StudentMapper;
import com.alta.mapper.TaskMapper;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BasicMainFacade implements MainFacade {
    private final TopicService topicService;
    private final TaskService taskService;
    private final StudentService studentService;

    private final StudentMapper studentMapper;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> findTasksUnfinishedForAllStudents(List<TopicDto> topicsDto, List<StudentDto> studentsDto) {
        List<Student> students = studentMapper.toStudentList(studentsDto);
        return filterOfUnfinishedTasks(topicsDto, students);
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

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    private List<TaskDto> filterOfUnfinishedTasks(List<TopicDto> topicsDto, List<Student> students) {
        List<Task> completedTasks = studentService.getTasks(students);
        return taskService.getUnfinishedTasks(topicsDto, completedTasks);
    }

    private List<TaskDto> filterOfTasksCompletedByAtLeastOneStudent(List<TopicDto> topicsDto, List<Student> students) {
        List<Task> completedTasks = studentService.getTasks(students);
        return taskService.getTasksCompletedByAtLeastOneStudent(topicsDto, completedTasks);
    }

    private Map<String, List<TaskDto>> assignTasks(List<Student> students, List<Task> tasksToAdd) {
        Map<String, List<TaskDto>> mapOfStudentsAndTasksAssigned = new HashMap<>();

        assignTasksAndFillInMapOfStudentsAndTasksAssigned(students, tasksToAdd, mapOfStudentsAndTasksAssigned);

        return mapOfStudentsAndTasksAssigned;
    }

    private void assignTasksAndFillInMapOfStudentsAndTasksAssigned(List<Student> students, List<Task> tasksToAdd, Map<String, List<TaskDto>> mapOfStudentsAndTasksAssigned) {
        students.forEach(student -> {
            List<Task> newTasks = taskService.excludeCompletedTasks(tasksToAdd, student);

            student.getTasks().addAll(tasksToAdd);
            studentService.save(student);

            mapOfStudentsAndTasksAssigned.put(studentMapper.toStudentDto(student).getFullName(),
                    taskMapper.toTaskDtoList(newTasks));
        });
    }
}
