package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.Task;
import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.TaskService;
import com.example.smartwebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.findAll());
        return "tasks.xhtml";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.findAll());
            model.addAttribute("users", userService.findAll());
            return "tasks.xhtml";
        }
        taskService.save(task);
        return "redirect:/tasks";
    }
}
