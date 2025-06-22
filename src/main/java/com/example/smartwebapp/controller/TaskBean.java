package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.Task;
import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.TaskService;
import com.example.smartwebapp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean JSF para la gestión de tareas.
 */
@Named("taskBean")
@RequestScoped
public class TaskBean implements Serializable {

    @Inject
    private TaskService taskService;
    
    @Inject
    private UserService userService;

    private Task task = new Task();
    private List<Task> tasks;
    private List<User> users;
    private Long selectedUserId;

    @PostConstruct
    public void init() {
        loadTasks();
        loadUsers();
    }

    public void save() {
        try {
            if (task.getDescription() != null && !task.getDescription().trim().isEmpty() && selectedUserId != null) {
                java.util.Optional<User> userOptional = userService.findById(selectedUserId);
                if (userOptional.isPresent()) {
                    task.setUser(userOptional.get());
                    taskService.save(task);
                    task = new Task(); // Reset form
                    selectedUserId = null;
                    loadTasks(); // Reload list
                }
            }
        } catch (Exception e) {
            // Handle error
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        tasks = taskService.findAll();
    }
    
    private void loadUsers() {
        users = userService.findAll();
    }

    // Getters and setters
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public Long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}

