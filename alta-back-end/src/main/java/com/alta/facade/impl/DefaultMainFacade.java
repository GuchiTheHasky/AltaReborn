package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TasksGroupDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Task;
import com.alta.entity.TaskStatus;
import com.alta.entity.TasksGroup;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.TaskGroupService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import com.alta.web.entity.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultMainFacade implements MainFacade {
    private final TopicService topicService;
    private final TaskService taskService;
    private final StudentService studentService;
    private final TaskGroupService taskGroupService;

    @Override
    public List<TopicDto> findAllTopics() {
        return topicService.findAll();
    }

    @Override
    public StudentDto findStudentById(int id) {
        return studentService.findById(id);
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TasksGroupDto findTaskGroupById(int id) {
        return taskGroupService.findById(id);
    }

    @Override
    public List<TasksGroupDto> findTasksGroupByStudentIds(List<Integer> studentsIds) {
        return taskGroupService.findByStudentIds(studentsIds);
    }

    @Override
    public List<TaskDto> findAllTasks(List<Integer> studentIds, List<Integer> topicIds) {
        List<TaskDto> tasks = taskService.findByTopicIds(topicIds);
        List<TasksGroupDto> tasksGroup = taskGroupService.findByStudentIds(studentIds);

        assignTaskStatus(tasksGroup, tasks);
        return sortTasksByStatus(tasks);
    }

    @Override
    public List<TaskResponse> receiveAssignmentTasks(List<Integer> studentsIds, List<Integer> tasksIds) {
        List<StudentDto> students = studentService.findAllByIds(studentsIds);
        List<Task> tasks = taskService.findAllByIds(tasksIds);

        return students.stream()
                .map(studentDto -> {
                    TasksGroup group = taskGroupService.createTasksGroup(studentDto.getId(), tasks);

                    return new TaskResponse(studentDto, taskGroupService.save(group));
                }).toList();
    }

    @Override
    public TaskDto updateTask(int id, TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }

    @Override
    public List<StudentDto> findAllStudentsPageByPage(PageRequest pageRequest) {
        return studentService.findAllStudentsPageByPage(pageRequest);
    }

    @Override
    public List<TopicDto> findAllTopicsPageByPage(PageRequest pageRequest) {
        return topicService.findAllTopicsPageByPage(pageRequest);
    }

    @Override
    public Page<TaskDto> findAllTasksPageByPage(List<Integer> studentIds, List<Integer> topicIds, PageRequest pageRequest) {
        List<TaskDto> tasks = taskService.findByTopicIds(topicIds);
        List<TasksGroupDto> tasksGroup = taskGroupService.findByStudentIds(studentIds);

        int start = (int) pageRequest.getOffset();
        int end = Math.min(start + pageRequest.getPageSize(), tasks.size());

        List<TaskDto> pageContent;

        if (start >= end) {
            pageContent = Collections.emptyList();
        } else {
            pageContent = tasks.subList(start, end);
        }

        assignTaskStatus(tasksGroup, pageContent);

        return new PageImpl<>(sortTasksByStatus(pageContent), pageRequest, tasks.size());
    }

    private void assignTaskStatus(List<TasksGroupDto> tasksGroup, List<TaskDto> tasks) {
        Set<Integer> assignedTasks = tasksGroup.stream()
                .flatMap(tasksGroupDto -> tasksGroupDto.getTasks().stream())
                .map(TaskDto::getId)
                .collect(Collectors.toSet());

        tasks.forEach(task -> {
            if (assignedTasks.contains(task.getId())) {
                task.setStatus(TaskStatus.ASSIGNED.getStatus());
            }
        });
    }

    private List<TaskDto> sortTasksByStatus(List<TaskDto> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(task -> TaskStatus.ASSIGNED.getStatus().equals(task.getStatus())))
                .collect(Collectors.toList());
    }

}
