package com.alta.service.impl;

import com.alta.entity.Task;
import com.alta.entity.TasksGroup;
import com.alta.exception.TaskGroupException;
import com.alta.repository.TaskGroupRepository;
import com.alta.service.TaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskGroupService implements TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;

    @Override
    public TasksGroup findById(int taskGroupId) {
        Optional<TasksGroup> optionalTasksGroup = taskGroupRepository.findById(taskGroupId);
        if (optionalTasksGroup.isEmpty()) {
            throw new TaskGroupException(taskGroupId);
        }
        return optionalTasksGroup.get();
    }

    @Override
    public List<TasksGroup> findByStudentIds(List<Integer> studentsIds) {
        return taskGroupRepository.findByStudentIds(studentsIds);

    }

    @Override
    public TasksGroup save(TasksGroup group) {
        return taskGroupRepository.save(group);
    }

    @Override
    public TasksGroup createTasksGroup(int studentId, List<Task> tasks) {
        TasksGroup group = new TasksGroup();
        group.setCreatingAt(creatingDate());
        group.setStudentId(studentId);

        List<Task> uniqueTasks = filterAssignedTasks(studentId, tasks);

        group.setTasks(uniqueTasks);
        return group;
    }

    private List<Task> filterAssignedTasks(int studentId, List<Task> tasks) {
        List<TasksGroup> groupsForStudent = taskGroupRepository.findByStudentId(studentId);

        Set<Integer> assignedTaskIds = groupsForStudent.stream()
                .flatMap(group -> group.getTasks().stream())
                .map(Task::getId)
                .collect(Collectors.toCollection(HashSet::new));

        return tasks.stream()
                .filter(task -> !assignedTaskIds.contains(task.getId()))
                .collect(Collectors.toList());
    }

    private LocalDateTime creatingDate() {
        return LocalDateTime.now().withNano(0);
    }
}
