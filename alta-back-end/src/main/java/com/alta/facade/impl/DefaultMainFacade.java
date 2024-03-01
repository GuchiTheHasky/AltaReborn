package com.alta.facade.impl;

import com.alta.dto.ExamDto;
import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.TaskStatus;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.ExamService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import com.alta.web.entity.ExamRequest;
import lombok.RequiredArgsConstructor;
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
    private final ExamService examService;

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
    public ExamDto findExamById(int id) {
        return examService.findById(id);
    }


    // to do -> need to change assignTaskStatus
//    @Override
//    public List<TaskDto> findAllTasks(List<Integer> studentIds, List<Integer> topicIds) {
//        List<TaskDto> tasks = taskService.findByTopicIds(topicIds);
//        List<ExamDto> exam = examService.findByStudentIds(studentIds);
//
//        assignTaskStatus(exam, tasks);
//        return sortTasksByStatus(tasks);
//    }


    // to do -> we don't need it anymore
//    @Override
//    public List<TaskResponse> receiveAssignmentTasks(List<Integer> studentsIds, List<Integer> tasksIds) {
//        List<StudentDto> students = studentService.findAllByIds(studentsIds);
//        List<Task> tasks = taskService.findAllByIds(tasksIds);
//
//        return students.stream()
//                .map(studentDto -> {
//                    Exam group = examService.createExam(studentDto.getId(), tasks);
//
//                    return new TaskResponse(studentDto, examService.save(group));
//                }).toList();
//    }

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


    // to do -> need to change assignTaskStatus
//    @Override
//    public Page<TaskDto> findAllTasksPageByPage(List<Integer> studentIds, List<Integer> topicIds, PageRequest pageRequest) {
//        List<TaskDto> tasks = taskService.findByTopicIds(topicIds);
//        List<ExamDto> exam = examService.findByStudentIds(studentIds);
//
//        int start = (int) pageRequest.getOffset();
//        int end = Math.min(start + pageRequest.getPageSize(), tasks.size());
//
//        List<TaskDto> pageContent;
//
//        if (start >= end) {
//            pageContent = Collections.emptyList();
//        } else {
//            pageContent = tasks.subList(start, end);
//        }
//
//        assignTaskStatus(exam, pageContent);
//
//        return new PageImpl<>(sortTasksByStatus(pageContent), pageRequest, tasks.size());
//    }

    @Override
    public List<ExamDto> findAllExams() {
        return examService.findAll();
    }

    @Override
    public List<ExamDto> findAllExamsPageByPage(PageRequest pageRequest) {
        return examService.findAllExamsPageByPage(pageRequest);
    }

    @Override
    public ExamDto createExam(ExamRequest request) {
        List<Student> students = studentService.findAllByIds(request.studentsIds());
        List<Task> tasks = taskService.findAllByIds(request.tasksIds());


        return examService.createExam(request.title(), students, tasks);
    }

    private void assignTaskStatus(List<ExamDto> exam, List<TaskDto> tasks) {
        Set<Integer> assignedTasks = exam.stream()
                .flatMap(examDto -> examDto.getTasks().stream())
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
