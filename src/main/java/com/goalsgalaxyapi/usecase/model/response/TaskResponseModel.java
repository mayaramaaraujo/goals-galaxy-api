package com.goalsgalaxyapi.usecase.model.response;

import com.goalsgalaxyapi.domain.model.Task;

public record TaskResponseModel(String name, String descripton, boolean done, Long goalId) {
    public TaskResponseModel(Task task) {
        this(task.getName(), task.getDescription(), task.isDone(), task.getGoal().getId());
    }
}
