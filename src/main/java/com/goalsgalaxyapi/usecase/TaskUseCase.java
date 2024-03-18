package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.model.TaskRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.TaskResponseModel;

import java.util.Optional;

public class TaskUseCase {

    TaskRepository taskRepository;
    GoalRepository goalRepository;

    public TaskUseCase(TaskRepository taskRepository, GoalRepository goalRepository) {
        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
    }

    public ResponseModel<TaskResponseModel> create(TaskRequestModel request, Long userId, Long goalId) {
        try {
            Optional<Goal> goal = goalRepository.findByIdAndUserId(goalId, userId);

            if(goal.isEmpty()) {
                return new ResponseModel<>(false,  "Goal or User do not exists", null);
            }

            Task newTask = new Task(request.name(), request.description(), goal.get());
            Task task = taskRepository.save(newTask);

            return new ResponseModel<>(true, null, new TaskResponseModel(task.getName(), task.getDescription(), task.isDone(), task.getGoal().getId()));
        } catch (Exception e) {
            return new ResponseModel<>(false, e.getMessage(), null);
        }
    }
}
