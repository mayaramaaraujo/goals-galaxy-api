package com.goalsgalaxyapi.api.controller;

import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.usecase.GoalUseCase;
import com.goalsgalaxyapi.usecase.model.request.GoalRequestModel;
import com.goalsgalaxyapi.usecase.model.response.GoalResponseModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/goals")
public class GoalController {

    private final GoalUseCase goalUseCase;
    public GoalController(GoalUseCase goalUseCase) {
        this.goalUseCase = goalUseCase;
    }

    @GetMapping
    public List<Goal> getAll() {
        return null;
    }

    @GetMapping("/{goalId}")
    public Goal get(@PathVariable Long goalId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<GoalResponseModel>> save(@Valid @RequestBody GoalRequestModel request, @PathVariable() Long userId) {
        ResponseModel<GoalResponseModel> response = goalUseCase.create(request, userId);

        if(!response.success()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public Goal update(@PathVariable Long id, @RequestBody Goal goal) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

}
