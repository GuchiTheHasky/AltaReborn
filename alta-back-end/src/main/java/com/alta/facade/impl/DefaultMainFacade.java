package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Task;
import com.alta.entity.TasksGroup;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.TaskGroupService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import com.alta.web.entity.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TasksGroup findTaskGroupById(int id) {
        return taskGroupService.findById(id);
    }

    @Override
    public List<TasksGroup> findTasksGroupByStudentIds(List<Integer> studentsIds) {
        return taskGroupService.findByStudentIds(studentsIds);
    }

    @Override
    public List<TaskDto> findAllTasks(List<Integer> studentIds, List<Integer> topicIds) {
        return taskService.findByTopicIds(topicIds);
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
    public List<TopicDto> findAllTopicsPageByPage(PageRequest pageRequest) {
        return topicService.findAllTopicsPageByPage(pageRequest);
    }

}
