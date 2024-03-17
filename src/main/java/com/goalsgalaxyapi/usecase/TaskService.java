package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.repository.TaskRepository;

public class TaskService {
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
