package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMainFacade implements MainFacade {

    private final TaskService taskService;
    private final StudentService studentService;

    @Override
    public List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, Integer studentId) {
        return filterOfUnfinishedTasks(topicIds, studentId);
    }

    @Override
    @Transactional
    public List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds) { // todo rename method
        assignTasks(studentId, taskIds);
        return taskService.findAllByIds(taskIds);
    }

    @Override
    public List<TaskDto> filterOfUnfinishedTasks(List<Integer> selectedTopicsIdList, Integer studentId) {
        Student student = studentService.findById(studentId);
        List<Task> completedTasks = student.getTasks();

        return taskService.getUnfinishedTasks(selectedTopicsIdList, completedTasks);
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        return taskService.update(taskDto);
    }


    void assignTasks(int id, List<Integer> tasks) {
        Student student = studentService.findById(id);
        List<Task> taskList = student.getTasks();
        List<Task> tasksToAdd = taskService.findAllById(tasks);
        taskList.addAll(tasksToAdd);
        studentService.save(student);
    }

}
