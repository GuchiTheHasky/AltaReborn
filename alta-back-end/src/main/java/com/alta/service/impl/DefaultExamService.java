package com.alta.service.impl;

import com.alta.dto.FullExamDto;
import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.entity.Exam;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.exception.ExamException;
import com.alta.mapper.ExamMapper;
import com.alta.repository.ExamRepository;
import com.alta.dto.ExamDto;
import com.alta.service.ExamService;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.web.entity.ExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    private final TaskService taskService;
    private final StudentService studentService;

    @Override
    public FullExamDto findById(int examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if (optionalExam.isEmpty()) {
            throw new ExamException(examId);
        }
        return examMapper.toDetailsExamDto(optionalExam.get());
    }

    @Override
    public FullExamDto createExam(ExamRequest request) {
        List<Student> students = extractStudents(request);
        List<Task> tasks = extractTasks(request);

        Exam exam = new Exam();
        exam.setName(request.name());
        exam.setCreatedAt(creatingDate());
        exam.setStudents(students);
        exam.setTasks(tasks);
        return examMapper.toDetailsExamDto(examRepository.save(exam));
    }

    @Override
    public List<ExamDto> findAll() {
        return examRepository.findAll().stream()
                .map(examMapper::toExamDto)
                .toList();
    }

    @Override
    public List<ExamDto> findAllExamsPageByPage(PageRequest pageRequest) {
        Optional<Page<Exam>> optionalExamsPage = Optional.of(examRepository.findAll(pageRequest));

        return optionalExamsPage.map(page ->
                page.getContent().stream()
                        .map(examMapper::toExamDto)
                        .sorted(Comparator.comparing(ExamDto::getName, Comparator.nullsLast(Comparator.naturalOrder())))
                        .toList()
        ).orElse(Collections.emptyList());
    }

    private LocalDateTime creatingDate() {
        return LocalDateTime.now().withNano(0);
    }

    private List<Student> extractStudents(ExamRequest request) {
        List<StudentDto> studentDtos = request.students();
        List<Integer> studentIds = studentDtos.stream()
                .map(StudentDto::getId)
                .toList();
        return studentService.findAllByIds(studentIds);
    }
    private List<Task> extractTasks(ExamRequest request) {
        List<TaskDto> taskDtos = request.tasks();
        List<Integer> taskIds = taskDtos.stream()
                .map(TaskDto::getId)
                .toList();
        return taskService.findAllByIds(taskIds);
    }
}
