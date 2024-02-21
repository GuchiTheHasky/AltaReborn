package com.alta.web.controller;

import com.alta.entity.TasksGroup;
import com.alta.facade.MainFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-group")
public class TaskGroupController {
    private final MainFacade mainFacade;

    @GetMapping("/{id}")
    public TasksGroup findById(@PathVariable("id") int id) {
        return mainFacade.findTaskGroupById(id);
    }

    @GetMapping("/studentsIds")
    public List<TasksGroup> findByStudentsIds(@RequestParam("id") List<Integer> studentsIds) {
        return mainFacade.findTasksGroupByStudentIds(studentsIds);
    }

    @GetMapping("/{id}/document")
    public ModelAndView getModelAndView(@PathVariable("id") int taskGroupId,
                                        @RequestParam("type") String type) {
        TasksGroup tg = mainFacade.findTaskGroupById(taskGroupId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(type);
        modelAndView.addObject("group", tg);
        return modelAndView;
    }
}
