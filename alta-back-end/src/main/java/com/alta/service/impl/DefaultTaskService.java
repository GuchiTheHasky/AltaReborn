package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.exception.TaskException;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository TASK_REPOSITORY;
    private final TaskMapper TASK_MAPPER;
    private final StudentService STUDENT_SERVICE;
    private final TopicService TOPIC_SERVICE;


    @Override
    public List<TaskDto> findAll() {
        return TASK_REPOSITORY.findAll().stream().map(TASK_MAPPER::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task newTask = TASK_MAPPER.toTask(taskDto);
        return TASK_MAPPER.toTaskDto(TASK_REPOSITORY.save(newTask));
    }

    @Override
    public void delete(int id) {
        TASK_REPOSITORY.findById(id).ifPresent(task -> TASK_REPOSITORY.deleteById(id));
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        return TASK_REPOSITORY.findById(id)
                .map(taskRequired -> {
                    taskRequired.setNumber(taskDto.getNumber());
                    taskRequired.setImagePath(taskDto.getImagePath());
                    taskRequired.setLevel(taskDto.getLevel());
                    taskRequired.setText(taskDto.getText());
                    taskRequired.setAnswer(taskDto.getAnswer());
                    return TASK_MAPPER.toTaskDto(TASK_REPOSITORY.save(taskRequired));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    @Override
    public Task findById(int taskId) {
        return TASK_REPOSITORY.findById(taskId).orElseThrow(() -> new TaskException(taskId));
    }

    @Override
    public TaskDto addStudentToTask(int taskId, int studentId) {
        Task task = findById(taskId);
        Student student = STUDENT_SERVICE.findById(studentId);
        task.addStudent(student);
        return save(TASK_MAPPER.toTaskDto(task));
    }

    @Override
    public TaskDto assignTopicToTask(int taskId, int topicId) {
        Task task = findById(taskId);
        Topic topic = TOPIC_SERVICE.findById(topicId);
        task.assignTopic(topic);
        return save(TASK_MAPPER.toTaskDto(task));
    }

    @Override
    public List<TaskDto> getTasksByTopicId(int topicId) {
        Topic topic = TOPIC_SERVICE.findById(topicId);
        List<Task> tasks = TASK_REPOSITORY.findByTopic(topic);
        return tasks.stream().map(TASK_MAPPER::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByStudentId(int studentId) {
        Student student = STUDENT_SERVICE.findById(studentId);
        List<Task> tasks = TASK_REPOSITORY.findAllByStudentsId(student.getId());
        return tasks.stream().map(TASK_MAPPER::toTaskDto).collect(Collectors.toList());
    }
}
