package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Task;
import com.alta.facade.MainFacade;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.web.entity.ModelRequest;
import com.alta.web.entity.TasksRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final MainFacade mainFacade;

//    private final TaskRepository taskRepository;
//    private final TaskMapper taskMapper;

    @PostMapping("/all")
    public List<TaskDto> findAllTasksIncludedInTopic(@RequestBody TasksRequest request) {
        List<Integer> studentsIds = request.getStudents().stream().map(StudentDto::getId).toList();
        List<Integer> topicsIds = request.getTopics().stream().map(TopicDto::getId).toList();
        System.out.println("controller: " + studentsIds);
        System.out.println(topicsIds);

        return mainFacade.findAllTasks(topicsIds, studentsIds);
    }

//    @PostMapping("/unfinished")
//    public TasksResponse findAllUnfinishedTasks(@RequestBody TasksRequest request) {
//        List<TopicDto> topicsDto = request.getTopics();
//        List<StudentDto> studentsDto = request.getStudents();
//
//        TasksResponse response = new TasksResponse();
//
//        response.setUnfinishedTasksForAllStudentsSelected(mainFacade.findTasksUnfinishedForAllStudents(topicsDto, studentsDto));
//        response.setTasksCompletedByAtLeastOneStudent(mainFacade.findTasksCompletedByAtLeastOneStudent(topicsDto, studentsDto));
//
//        return response;
//    }

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
        return mainFacade.updateTask(id, taskDto);
    }
}
