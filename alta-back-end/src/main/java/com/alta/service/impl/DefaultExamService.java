package com.alta.service.impl;

import com.alta.dto.ExamDto;
import com.alta.entity.Exam;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.exception.ExamException;
import com.alta.mapper.ExamMapper;
import com.alta.repository.ExamRepository;
import com.alta.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Override
    public ExamDto findById(int examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if (optionalExam.isEmpty()) {
            throw new ExamException(examId);
        }
        return examMapper.toExamDto(optionalExam.get());
    }

        // to do -> do we need it?
//    @Override
//    public List<ExamDto> findByStudentIds(List<Integer> studentsIds) {
//        return examRepository.findByStudentIds(studentsIds).stream().map(examMapper::toExamDto).toList();
//
//    }

    @Override
    public ExamDto createExam(String title, List<Student> students, List<Task> tasks) {
        Exam exam = new Exam();
        exam.setTitle(title);
        exam.setCreatedAt(creatingDate());
        exam.setStudents(students);
        exam.setTasks(tasks);
        return examMapper.toExamDto(examRepository.save(exam));
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
                        .sorted(Comparator.comparing(ExamDto::getTitle, Comparator.nullsLast(Comparator.naturalOrder())))
                        .toList()
        ).orElse(Collections.emptyList());
    }

//    private List<Task> filterAssignedTasks(int studentId, List<Task> tasks) {
//        List<Exam> groupsForStudent = examRepository.findByStudentId(studentId);
//
//        Set<Integer> assignedTaskIds = groupsForStudent.stream()
//                .flatMap(group -> group.getTasks().stream())
//                .map(Task::getId)
//                .collect(Collectors.toCollection(HashSet::new));
//
//        return tasks.stream()
//                .filter(task -> !assignedTaskIds.contains(task.getId()))
//                .collect(Collectors.toList());
//    }

    private LocalDateTime creatingDate() {
        return LocalDateTime.now().withNano(0);
    }
}
