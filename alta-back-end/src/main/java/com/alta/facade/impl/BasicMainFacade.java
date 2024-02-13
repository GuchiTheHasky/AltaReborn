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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BasicMainFacade implements MainFacade {
    private final TopicService topicService;
    private final TaskService taskService;
    private final StudentService studentService;

    private final StudentMapper studentMapper;
    private final TaskMapper taskMapper;

//    @Override
//    public List<TaskDto> findTasksUnfinishedForAllStudents(List<TopicDto> topicsDto, List<StudentDto> studentsDto) {
//        List<Student> students = studentMapper.toStudentList(studentsDto);
//        return filterOfUnfinishedTasks(topicsDto, students);
//    }

    @Override
    public Page<TaskDto> findTasksUnfinishedForAllStudents(List<TopicDto> topicsDto, List<StudentDto> studentsDto, PageRequest pageRequest) {
        List<Student> students = studentMapper.toStudentList(studentsDto);
        return filterOfUnfinishedTasks(topicsDto, students, pageRequest);
    }

    @Override
    @Transactional
    public Map<String, List<TaskDto>> updateStudentTasksAndRetrieveDto(List<StudentDto> studentsDto, List<TaskDto> tasksDto) { // todo rename method
        List<Student> students = studentMapper.toStudentList(studentsDto);
        List<Task> tasksToAdd = taskMapper.toTaskList(tasksDto);

        Map<String, List<TaskDto>> mapOfStudentsAndTasksAssigned = new HashMap<>();

        assignTasksAndFillInMapOfStudentsAndTasksAssigned(students, tasksToAdd, mapOfStudentsAndTasksAssigned);

        return mapOfStudentsAndTasksAssigned;
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
    public TaskDto updateTask(int id, TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }

//    private List<TaskDto> filterOfUnfinishedTasks(List<TopicDto> topicsDto, List<Student> students) {
//        Set<Task> completedTasks = studentService.getTasks(students);
//        return taskService.getUnfinishedTasks(topicsDto, completedTasks);
//    }

    private Page<TaskDto> filterOfUnfinishedTasks(List<TopicDto> topicsDto, List<Student> students, PageRequest pageRequest) {
        Set<Task> completedTasks = studentService.getTasks(students);
        List<TaskDto> allUnfinishedTasks = taskService.getUnfinishedTasks(topicsDto, completedTasks);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), allUnfinishedTasks.size());

        return new PageImpl<>(allUnfinishedTasks.subList(start, end), pageRequest, allUnfinishedTasks.size());
    }

    private List<TaskDto> filterOfTasksCompletedByAtLeastOneStudent(List<TopicDto> topicsDto, List<Student> students) {
        Set<Task> completedTasks = studentService.getTasks(students);
        return taskService.getTasksCompletedByAtLeastOneStudent(topicsDto, completedTasks);
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
