package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMainFacade implements MainFacade {
    private final TopicService topicService;
    private final TaskService taskService;
    private final StudentService studentService;

    @Override
    public List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, List<Integer> studentsIds) {
        return filterOfUnfinishedTasks(topicIds, studentsIds);
    }

    @Override
    @Transactional
    public List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds) { // todo rename method
        assignTasks(studentId, taskIds);
        return taskService.findAllByIds(taskIds);
    }

    @Override
    public List<TopicDto> findAllTopics() {
        return topicService.findAll();
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    //    @Override
//    public List<TaskDto> filterOfUnfinishedTasks(List<Integer> selectedTopicsIdList, List<Integer> studentId) {
//        Student student = studentService.findById(studentId);
//        List<Task> completedTasks = student.getTasks();
//
//        return taskService.getUnfinishedTasks(selectedTopicsIdList, completedTasks);
//    }

    List<TaskDto> filterOfUnfinishedTasks(List<Integer> selectedTopicsIdList, List<Integer> studentsIds) {
        List<Student> students = studentService.findAllById(studentsIds);
        List<Task> completedTasks = studentService.getTasks(students);

        return taskService.getUnfinishedTasks(selectedTopicsIdList, completedTasks);
    }

    void assignTasks(int id, List<Integer> tasks) {
        Student student = studentService.findById(id);
        List<Task> taskList = student.getTasks();
        List<Task> tasksToAdd = taskService.findAllById(tasks);
        taskList.addAll(tasksToAdd);
        studentService.save(student);
    }

}
