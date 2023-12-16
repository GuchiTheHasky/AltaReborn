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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final StudentService studentService;
    private final TopicService topicService;


    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task newTask = taskMapper.toTask(taskDto);
        return taskMapper.toTaskDto(taskRepository.save(newTask));
    }

    @Override
    public void delete(int id) {
        taskRepository.findById(id).ifPresent(task -> taskRepository.deleteById(id));
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(taskRequired -> {
                    taskRequired.setDescription(taskDto.getDescription());
                    taskRequired.setAnswer(taskDto.getAnswer());
                    taskRequired.setImageUrl(taskDto.getImageUrl());
                    taskRequired.setLevel(taskDto.getLevel());
                    return taskMapper.toTaskDto(taskRepository.save(taskRequired));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    Task findById(int taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskException(taskId));
    }

    @Override
    public TaskDto assignStudentToTask(int taskId, int studentId) {
        Task task = findById(taskId);
        Student student = studentService.findById(studentId);
        task.addStudent(student);
        return save(taskMapper.toTaskDto(task));
    }

    @Override
    public TaskDto assignTopicToTask(int taskId, int topicId) {
        Task task = findById(taskId);
        Topic topic = topicService.findById(topicId);
        task.setTopic(topic);
        return save(taskMapper.toTaskDto(task));
    }

    @Override
    public List<TaskDto> findAllByTopicId(int topicId) {
        return taskRepository.findAllByTopicId(topicId)
                .stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllByStudentId(int studentId) {
        return taskRepository.findAllByStudentsId(studentId)
                .stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllByTopicIdList(List<Integer> topicIdList) {
        return topicIdList.stream()
                .map(taskRepository::findAllByTopicId)
                .flatMap(Collection::stream)
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudentFromTasks(Student student) {
        student.getTasks().forEach(task ->
                task.getStudents()
                        .remove(student));
    }
}
