package com.shurankain.planning.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String taskInfo) {
        return taskRepository.insert(Task.builder()
                .taskInfo(taskInfo)
                .creationDate(LocalDateTime.now())
                .completionStatus(false)
                .build());
    }
}
