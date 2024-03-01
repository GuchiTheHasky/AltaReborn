package com.alta.facade.impl;

import com.alta.dto.*;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.TaskStatus;
import com.alta.entity.Zno;
import com.alta.facade.MainFacade;
import com.alta.mapper.TaskMapper;
import com.alta.service.*;
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
    private final ZnoService znoService;
    private final TaskMapper taskMapper;

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

    @Override
    public List<ZnoDto> findAllZnos() {
        return znoService.findAll();
    }

    @Override
    public List<ZnoDto> findAllZnosPageByPage(PageRequest pageRequest) {
        return znoService.findAllZnosPageByPage(pageRequest);
    }

    @Override
    public List<TaskDto> findTasksByZnoId(int id) {
        Zno zno = znoService.findById(id);
        return zno.getTasks().stream()
                .map(taskMapper::toTaskDto)
                .toList();
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
