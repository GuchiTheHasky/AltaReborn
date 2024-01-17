package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.exception.TaskException;
import com.alta.exception.TopicException;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.repository.TopicRepository;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicService topicService;
    private final TopicRepository topicRepository;

    @Override
    @Transactional
    public TaskDto update(int id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new TaskException("Task not found with id: " + taskDto.getId()));

        // Оновити дані з DTO
        existingTask.setLevel(taskDto.getLevel());
        existingTask.setAnswer(taskDto.getAnswer());
        existingTask.setTitle(taskDto.getTitle());

        // Отримати нову тему за допомогою її ідентифікатора
        Topic newTopic = topicRepository.findById(taskDto.getTopicId())
                .orElseThrow(() -> new TopicException("Topic not found with id: " + taskDto.getTopicId()));

        existingTask.setTopic(newTopic);


        // Перевірити, чи існує поточна тема у задачі та видалити її
        //Topic currentTopic = existingTask.getTopic();
        //if (currentTopic != null) {
        //    currentTopic.getTasks().remove(existingTask);
            //topicRepository.save(currentTopic);
        //}

        // Оновити тему у задачі та додати задачу до нової теми
        //existingTask.setTopic(newTopic);
        //newTopic.getTasks().add(existingTask);

        // Зберегти оновлену задачу
        //taskRepository.save(existingTask);
        //topicRepository.save(newTopic);
        return taskMapper.toTaskDto(taskRepository.save(existingTask));
    }

    @Override
    public List<TaskDto> findAllByIds(List<Integer> taskIds) {
        return taskRepository.findAllByIds(taskIds).stream()
                .filter(task -> !task.isDeleted()).map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto findById(Integer taskId) {
        return taskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(taskId)));
    }

    @Override
    public Task findTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(id));
    }
}
