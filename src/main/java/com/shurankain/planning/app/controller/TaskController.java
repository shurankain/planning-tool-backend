package com.shurankain.planning.app.controller;

import com.shurankain.planning.app.dto.TaskDto;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        var task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping("/tasks/total")
    public ResponseEntity<Long> getTotalTasksAmount() {
        var tasks = taskService.getTotalTasksAmount();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/tasks/uncompleted")
    public ResponseEntity<Long> getUncompletedTasksAmount() {
        var tasks = taskService.getUncompletedTasksAmount();
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping("/tasks/add")
    public ResponseEntity<Task> addTask(@RequestParam String taskInfo) {
        var insertedTask = taskService.addTask(taskInfo);
        return ResponseEntity.ok().body(insertedTask);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> editTask(@PathVariable String id, @RequestBody TaskDto taskDto) {
        var task = taskService.editTask(id, taskDto);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
