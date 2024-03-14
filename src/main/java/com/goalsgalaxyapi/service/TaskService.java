package com.goalsgalaxyapi.service;

import com.goalsgalaxyapi.domain.repository.TaskRepository;

public class TaskService {
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
