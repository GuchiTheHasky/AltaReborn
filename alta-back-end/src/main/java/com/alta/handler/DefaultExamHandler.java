package com.alta.handler;

import com.alta.dto.FullExamDto;
import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.service.StudentService;
import com.alta.service.ExamService;
import com.alta.service.TaskService;
import com.alta.web.entity.ExamRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DefaultExamHandler implements ExamHandler {
    private final TaskService taskService;
    private final StudentService studentService;
    private final ExamService examService;

    @Override
    @Transactional
    public FullExamDto createExam(ExamRequest request) {
        List<StudentDto> studentDtos = request.students();
        List<TaskDto> taskDtos =request.tasks();

        List<Integer> studentIds = studentDtos.stream()
                .map(StudentDto::getId)
                .toList();

        List<Integer> taskIds = taskDtos.stream()
                .map(TaskDto::getId)
                .toList();

        List<Student> students = studentService.findAllByIds(studentIds);
        List<Task> tasks = taskService.findAllByIds(taskIds);

        return examService.createExam(request);
    }

}
