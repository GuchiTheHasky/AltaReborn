package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.facade.MainFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final MainFacade mainFacade;


//    @GetMapping("/unfinished")
//    public List<TaskDto> findAllUnfinishedTasks(
//            @RequestParam(name = "topics", required = false) List<Integer> topics,
//            @RequestParam(name = "student", required = false) Integer studentId) {
//
//        return mainFacade.findUnfinishedTasks(topics, studentId);
//
//    }

    @GetMapping("/unfinished")
    public List<TaskDto> findAllUnfinishedTasks(
            @RequestParam(name = "topics", required = false) List<Integer> topics,
            @RequestParam(name = "student", required = false) List<Integer> studentsIds) {

        return mainFacade.findUnfinishedTasks(topics, studentsIds);
    }

//    @GetMapping("/test")
//    public List<TaskDto> testTasks(
//            @RequestParam(name = "topics", required = false) List<Integer> topics,
//            @RequestParam(name = "student", required = false) Integer studentId) {
//        System.out.println("topics: " + topics);
//        System.out.println("stud id: " + studentId);
//        return mainFacade.findUnfinishedTasks(topics, studentId);
//
//    }

    @GetMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model,
                                          @RequestParam(name = "tasks") List<Integer> tasks,
                                          @RequestParam(name = "student") Integer studentId) {
        System.out.println("tasks: " + tasks);
        System.out.println("stud id: " + studentId);

        List<TaskDto> tasksWithAnswers = mainFacade.updateStudentTasksAndRetrieveDto(studentId, tasks);

        model.addAttribute("tasksWithAnswers", tasksWithAnswers);
        return new ModelAndView("task_list_answers", model);
    }


    @GetMapping("/noAnswers")
    public ModelAndView findAll(ModelMap model,
                                @RequestParam(name = "tasks") List<Integer> tasks,
                                @RequestParam(name = "student") Integer studentId)  {

        List<TaskDto> taskList = mainFacade.updateStudentTasksAndRetrieveDto(studentId, tasks);
        model.addAttribute("taskList", taskList);
        return new ModelAndView("task_list", model);
    }

    @PutMapping("/{taskId}")
    public TaskDto testUpdate(@PathVariable int taskId, @RequestBody TaskDto taskDto) {

        System.out.println("id: " + taskId);
        System.out.println("taskDto: " + taskDto);
        return mainFacade.updateTask(taskDto);
    }
}
