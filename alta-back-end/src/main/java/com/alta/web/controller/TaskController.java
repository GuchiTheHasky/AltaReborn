package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import com.alta.service.StudentService;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
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


    @GetMapping("/unfinished")
    public List<TaskDto> fetchData(
            @RequestParam(name = "topics", required = false) List<Integer> topics,
            @RequestParam(name = "student", required = false) Integer studentId) {

        return mainFacade.findUnfinishedTasks(topics, studentId);
    }

    @GetMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model,
                                          @RequestParam(name = "tasks") List<Integer> tasks,
                                          @RequestParam(name = "student") Integer studentId) {

        List<TaskDto> tasksWithAnswers = mainFacade.updateStudentTasksAndRetrieveDto(studentId, tasks);

        model.addAttribute("tasksWithAnswers", tasksWithAnswers);
        return new ModelAndView("task_list_answers", model);
    }


    @GetMapping // todo: update method, make it work with new facade, use 'findAllWithAnswer' as example;
    public ModelAndView findAll(ModelMap model,
                                @RequestParam(name = "tasks") List<Integer> tasks,
                                @RequestParam(name = "student") Integer studentId)  {

        List<TaskDto> taskList = mainFacade.updateStudentTasksAndRetrieveDto(studentId, tasks);
        model.addAttribute("taskList", taskList);
        return new ModelAndView("task_list", model);
    }


//    @PutMapping("/update/{id}") // todo: update method, make it work with new facade.
//    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
//        return taskService.update(id, taskDto);
//    }
}
