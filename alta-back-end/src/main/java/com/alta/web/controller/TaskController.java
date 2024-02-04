package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.ModelRequest;
import com.alta.web.entity.TasksRequest;
import com.alta.web.entity.TasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final MainFacade mainFacade;

    @PostMapping("/unfinished")
    public TasksResponse findAllUnfinishedTasks(@RequestBody TasksRequest request) {
        List<TopicDto> topics = request.getTopics();
        List<StudentDto> studentsDto = request.getStudents();

        TasksResponse response = new TasksResponse();

        response.setUnfinishedTasksForAllStudentsSelected(mainFacade.findUnfinishedTasks(topics, studentsDto));
        response.setTasksCompletedByAtLeastOneStudent(mainFacade.findTasksCompletedByAtLeastOneStudent(topics, studentsDto));

        return response;
    }

    @PostMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model, @RequestBody ModelRequest request) {
        System.out.println("tasks: " + request.getTasks());
        System.out.println("stud id: " + request.getStudent());

        List<TaskDto> tasks = request.getTasks();
        List<StudentDto> studentsDto = request.getStudent();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsDto, tasks);

        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list_answers", model);
    }


    @GetMapping("/noAnswers")
    public ModelAndView findAll(ModelMap model, @RequestBody ModelRequest request) {
        List<TaskDto> tasks = request.getTasks();
        List<StudentDto> studentsDto = request.getStudent();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsDto, tasks);
        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list", model); // not done yet
    }


    @PutMapping("/{taskId}")
    public TaskDto testUpdate(@PathVariable int taskId, @RequestBody TaskDto taskDto) {

        System.out.println("id: " + taskId);
        System.out.println("taskDto: " + taskDto);
        return mainFacade.updateTask(taskDto);
    }
}
