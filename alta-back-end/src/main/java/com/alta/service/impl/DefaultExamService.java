package com.alta.service.impl;

import com.alta.dto.*;
import com.alta.entity.Exam;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.exception.ExamException;
import com.alta.mapper.ExamMapper;
import com.alta.repository.ExamRepository;
import com.alta.service.ExamService;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.web.entity.ExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        return examMapper.toFullExamDto(optionalExam.get());
    }

    @Override
    public FullExamDto findByIdWithAnswers(int examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if (optionalExam.isEmpty()) {
            throw new ExamException(examId);
        }
        Exam exam = optionalExam.get();
        FullExamDto fullExamDto = examMapper.toFullExamDto(exam);
        List<TaskWithAnswerDto> tasksWithAnswers = exam.getTasks().stream()
                .map(task -> new TaskWithAnswerDto(
                        task.getId(),
                        task.getImagePath(),
                        task.getLevel(),
                        task.getTitle(),
                        task.getAnswer()))
                .collect(Collectors.toList());

        fullExamDto.setTasks(tasksWithAnswers);
        return fullExamDto;
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
        return examMapper.toFullExamDto(examRepository.save(exam));
    }

    @Override
    public List<ExamDto> findAll(Integer page, Integer size) {
        return Optional.ofNullable(page).isEmpty() || Optional.ofNullable(size).isEmpty() ? findAllExams() : findAllExams(page, size);
    }

    private List<ExamDto> findAllExams() {
        return examRepository.findAll().stream()
                .map(examMapper::toExamDto)
                .toList();
    }

    private List<ExamDto> findAllExams(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Exam> optionalExamsPage = examRepository.findAll(pageable);

        return optionalExamsPage.stream()
                .map(examMapper::toExamDto)
                .toList();
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
