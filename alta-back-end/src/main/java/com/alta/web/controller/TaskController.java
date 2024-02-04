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
        List<Integer> topicsIds = request.getTopics().stream().map(TopicDto::getId).toList();
        List<Integer> studentsIds = request.getStudents().stream().map(StudentDto::getId).toList();

        TasksResponse response = new TasksResponse();

        response.setUnfinishedTasksForAllStudentsSelected(mainFacade.findUnfinishedTasks(topicsIds, studentsIds));
        response.setTasksCompletedByAtLeastOneStudent(mainFacade.findTasksCompletedByAtLeastOneStudent(topicsIds, studentsIds));

        return response;
    }

    @PostMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model, @RequestBody ModelRequest request) {
        System.out.println("tasks: " + request.getTasks());
        System.out.println("stud id: " + request.getStudent());

        List<Integer> tasksIds = request.getTasks().stream().map(TaskDto::getId).toList();
        List<Integer> studentsIds = request.getStudent().stream().map(StudentDto::getId).toList();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsIds, tasksIds);

        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list_answers", model);
    }


    @GetMapping("/noAnswers")
    public ModelAndView findAll(ModelMap model, @RequestBody ModelRequest request)  {
        List<Integer> tasksIds = request.getTasks().stream().map(TaskDto::getId).toList();
        List<Integer> studentsIds = request.getStudent().stream().map(StudentDto::getId).toList();

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsIds, tasksIds);
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
