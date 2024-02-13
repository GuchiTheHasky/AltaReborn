package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.ModelRequest;
import com.alta.web.entity.TasksRequest;
import com.alta.web.entity.TasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final MainFacade mainFacade;

//    @PostMapping("/unfinished")
//    public TasksResponse findAllUnfinishedTasks(@RequestBody TasksRequest request) {
//        List<TopicDto> topicsDto = request.getTopics();
//        List<StudentDto> studentsDto = request.getStudents();
//
//        List<TaskDto> unfinishedTasksForAllStudentsSelected = mainFacade.findTasksUnfinishedForAllStudents(topicsDto, studentsDto);
//        List<TaskDto> tasksCompletedByAtLeastOneStudent = mainFacade.findTasksCompletedByAtLeastOneStudent(topicsDto, studentsDto);
//
//        return new TasksResponse(unfinishedTasksForAllStudentsSelected, tasksCompletedByAtLeastOneStudent);
//    }

    @PostMapping("/unfinished")
    public TasksResponse findAllUnfinishedTasks(@RequestBody TasksRequest request,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        List<TopicDto> topicsDto = request.getTopics();
        List<StudentDto> studentsDto = request.getStudents();

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<TaskDto> unfinishedTasksPage = mainFacade.findTasksUnfinishedForAllStudents(topicsDto, studentsDto, pageRequest);
        List<TaskDto> unfinishedTasks = unfinishedTasksPage.getContent();

        List<TaskDto> tasksCompletedByAtLeastOneStudent = mainFacade.findTasksCompletedByAtLeastOneStudent(topicsDto, studentsDto);

        return new TasksResponse(unfinishedTasks, tasksCompletedByAtLeastOneStudent);
    }

    @PostMapping("/answers")
    public ModelAndView findAssignedTasksByStudentNameWithAnswers(ModelMap model, @RequestBody ModelRequest request) {
        System.out.println("tasks: " + request.getTasks());
        System.out.println("stud id: " + request.getStudent());

        List<TaskDto> tasksDto = request.getTasks();
        List<StudentDto> studentsDto = request.getStudent();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsDto, tasksDto);

        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list_answers", model);
    }

    @PostMapping("/noAnswers")
    public ModelAndView findAssignedTasksByStudentNameWithoutAnswers(ModelMap model, @RequestBody ModelRequest request) {
        List<TaskDto> tasks = request.getTasks();
        List<StudentDto> studentsDto = request.getStudent();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsDto, tasks);
        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list", model);
    }

    @PutMapping("/{id}")
    public TaskDto testUpdate(@PathVariable int id, @RequestBody TaskDto taskDto) {

        System.out.println("id: " + id);
        System.out.println("taskDto: " + taskDto);
        return mainFacade.updateTask(id, taskDto);
    }
}
