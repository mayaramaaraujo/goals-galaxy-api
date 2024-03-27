package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.usecase.model.request.TaskRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.TaskResponseModel;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskUseCase {

    TaskRepository taskRepository;
    GoalRepository goalRepository;
    UserUseCase userUseCase;
    GoalUseCase goalUseCase;

    public TaskUseCase(TaskRepository taskRepository, GoalRepository goalRepository, UserUseCase userUseCase, GoalUseCase goalUseCase) {
        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
        this.userUseCase = userUseCase;
        this.goalUseCase = goalUseCase;
    }

    public ResponseModel<TaskResponseModel> create(TaskRequestModel request, Long userId) {
        try {
            Optional<Goal> goal = goalRepository.findByIdAndUserId(request.goalId(), userId);

            if(goal.isEmpty()) {
                return new ResponseModel<>(false,  HttpStatus.NOT_FOUND, "Goal or User do not exists", null);
            }

            Task newTask = new Task(request.name(), request.description(), goal.get());
            Task task = taskRepository.save(newTask);

            return new ResponseModel<>(true, HttpStatus.CREATED,null, new TaskResponseModel(task));
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    public ResponseModel<TaskResponseModel> getTask(Long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            return task.map(value -> new ResponseModel<>(true, HttpStatus.OK, null, new TaskResponseModel(task.get())))
                    .orElseGet(() -> new ResponseModel<>(false, HttpStatus.NOT_FOUND, "Task not found", null));
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    public ResponseModel<List<TaskResponseModel>> getAll(Long goalId, Long userId) {
        try {

            if(!goalUseCase.getGoal(userId, goalId).success()) {
                return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, "User or goal not found", null);
            }

            List<Task> tasks = taskRepository.findAllByGoalId(goalId);
            List<TaskResponseModel> response = new ArrayList<>();

            for (Task task : tasks) {
                response.add(new TaskResponseModel(task));
            }

            return new ResponseModel<>(true, HttpStatus.OK, null, response);
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
