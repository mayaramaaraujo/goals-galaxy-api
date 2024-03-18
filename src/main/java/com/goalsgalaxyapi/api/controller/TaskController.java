package com.goalsgalaxyapi.api.controller;

import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.usecase.TaskUseCase;
import com.goalsgalaxyapi.usecase.model.TaskRequestModel;
import com.goalsgalaxyapi.usecase.model.response.GoalResponseModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.TaskResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/goals/{goalId}/tasks")
public class TaskController {

    TaskUseCase taskUseCase;

    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @GetMapping
    public List<Task> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<TaskResponseModel>> save(@PathVariable Long userId, @PathVariable Long goalId, @RequestBody TaskRequestModel request) {
        ResponseModel<TaskResponseModel> response = taskUseCase.create(request, userId, goalId);

        if(!response.success()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
