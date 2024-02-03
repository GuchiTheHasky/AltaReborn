package com.alta.service.impl;

import com.alta.dto.StudentDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.exception.StudentException;
import com.alta.mapper.StudentMapper;
import com.alta.repository.StudentRepository;
import com.alta.repository.TaskRepository;
import com.alta.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentException(studentId));
    }

    @Override
    public List<Student> findAllById(List<Integer> studentsIds) {
        return studentRepository.findAllById(studentsIds);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Task> getTasks(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getTasks().stream())
                .distinct()
                .collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public void assignTasks(int id, List<Integer> tasks) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentException(id));
//        List<Task> taskList = student.getTasks();
//        List<Task> tasksToAdd = taskRepository.findAllById(tasks);
//        taskList.addAll(tasksToAdd);
//        // studentRepository.updateTasksIds(id, tasks.toString());
//        studentRepository.save(student);
//    }
}
