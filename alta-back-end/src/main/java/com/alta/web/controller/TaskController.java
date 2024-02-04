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


//    @GetMapping("/unfinished")
//    public List<TaskDto> findAllUnfinishedTasks(
//            @RequestParam(name = "topics", required = false) List<Integer> topics,
//            @RequestParam(name = "student", required = false) Integer studentId) {
//
//        return mainFacade.findUnfinishedTasks(topics, studentId);
//
//    }

//    @PostMapping("/unfinished")
//    public List<TaskDto> findAllUnfinishedTasks(@RequestBody TasksRequest request) {
//        List<Integer> topics = request.getTopics();
//        List<Integer> studentsIds = request.getStudents();
//
//        System.out.println("topics: " + topics);
//        System.out.println("studentsIds: " + studentsIds);
//
//        return mainFacade.findUnfinishedTasks(topics, studentsIds);
//    }

    @PostMapping("/unfinished")
    public TasksResponse findAllUnfinishedTasks(@RequestBody TasksRequest request) {
        List<Integer> topics = request.getTopics().stream().map(TopicDto::getId).collect(Collectors.toList());
        List<Integer> studentsIds = request.getStudents().stream().map(StudentDto::getId).collect(Collectors.toList());

        TasksResponse response = new TasksResponse();
        response.setUnfinishedTasksForAllStudentsSelected(mainFacade.findUnfinishedTasks(topics, studentsIds));
        response.setTasksCompletedByAtLeastOneStudent(mainFacade.findTasksCompletedByAtLeastOneStudent(topics, studentsIds));

        return response;
    }


//    @GetMapping("/unfinished")
//    public List<TaskDto> findAllUnfinishedTasks(
//            @RequestParam(name = "topics", required = false) List<TopicDto> topics,
//            @RequestParam(name = "student", required = false) List<StudentDto> students) {
//
//        return mainFacade.findUnfinishedTasks(topics, students);
//    }


//    @GetMapping("/test")
//    public List<TaskDto> testTasks(
//            @RequestParam(name = "topics", required = false) List<Integer> topics,
//            @RequestParam(name = "student", required = false) Integer studentId) {
//        System.out.println("topics: " + topics);
//        System.out.println("stud id: " + studentId);
//        return mainFacade.findUnfinishedTasks(topics, studentId);
//
//    }

    @PostMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model, @RequestBody ModelRequest request) {
        System.out.println("tasks: " + request.getTasks());
        System.out.println("stud id: " + request.getStudent());

        List<Integer> tasks = request.getTasks().stream().map(TaskDto::getId).collect(Collectors.toList());
        List<Integer> studentsIds = request.getStudent().stream().map(StudentDto::getId).collect(Collectors.toList());

        Map<String, List<TaskDto>> mapOfStudentsAndTasks = mainFacade.updateStudentTasksAndRetrieveDto(studentsIds, tasks);

        model.addAttribute("mapOfStudentsAndTasks", mapOfStudentsAndTasks);
        return new ModelAndView("task_list_answers", model);
    }


    @GetMapping("/noAnswers")
    public ModelAndView findAll(ModelMap model,
                                @RequestParam(name = "tasks") List<Integer> tasks,
                                @RequestParam(name = "student") List<Integer> studentsIds)  {

        Map<String, List<TaskDto>> taskList = mainFacade.updateStudentTasksAndRetrieveDto(studentsIds, tasks);
        model.addAttribute("taskList", taskList);
        return new ModelAndView("task_list", model);
    }


    @PutMapping("/{id}")
    public TaskDto testUpdate(@PathVariable int id, @RequestBody TaskDto taskDto) {

        System.out.println("id: " + id);
        System.out.println("taskDto: " + taskDto);
        return mainFacade.updateTask(taskDto);
    }
}
