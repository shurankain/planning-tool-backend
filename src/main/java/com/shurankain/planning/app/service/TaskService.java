package com.shurankain.planning.app.service;

import com.shurankain.planning.app.dto.TaskDto;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElse(null);
    }

    public Long getTotalTasksAmount() {
        return taskRepository.count();
    }

    public Long getUncompletedTasksAmount() {
        return taskRepository.countAllByCompletionStatusIsFalse();
    }

    public Task addTask(String taskInfo) {
        return taskRepository.insert(constructTask(taskInfo));
    }

    public Task editTask(String id, TaskDto taskDto) {
        var retrievedTask = getTaskById(id);
        retrievedTask.setTaskInfo(taskDto.getTaskInfo());
        retrievedTask.setCompletionStatus(taskDto.getCompletionStatus());
        return taskRepository.save(retrievedTask);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    private Task constructTask(String taskInfo) {
        return Task.builder()
                .taskInfo(taskInfo)
                .creationDate(LocalDateTime.now())
                .completionStatus(false)
                .build();
    }
}
