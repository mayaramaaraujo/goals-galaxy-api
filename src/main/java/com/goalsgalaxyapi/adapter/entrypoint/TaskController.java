package com.goalsgalaxyapi.adapter.entrypoint;

import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.usecase.TaskUseCase;
import com.goalsgalaxyapi.usecase.model.request.TaskRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.TaskResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskUseCase taskUseCase;

    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseModel<List<TaskResponseModel>>> getAll(@PathVariable Long goalId, @PathVariable Long userId) {
        ResponseModel<List<TaskResponseModel>> response = taskUseCase.getAll(goalId, userId);

        if(!response.success()) {
            return ResponseEntity.status(response.code()).body(response);
        }

        return ResponseEntity.status(response.code()).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<TaskResponseModel>> get(@PathVariable Long id) {
        ResponseModel<TaskResponseModel> response = taskUseCase.getTask(id);

        if(!response.success()) {
           return ResponseEntity.status(response.code()).body(response);
        }

        return ResponseEntity.status(response.code()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<TaskResponseModel>> save(@RequestBody TaskRequestModel request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User userDetails = (User) authentication.getPrincipal();

        ResponseModel<TaskResponseModel> response = taskUseCase.create(request, userDetails.getId());

        if(!response.success()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
