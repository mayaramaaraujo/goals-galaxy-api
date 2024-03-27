package com.goalsgalaxyapi.adapter.entrypoint;

import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.usecase.GoalUseCase;
import com.goalsgalaxyapi.usecase.model.request.GoalRequestModel;
import com.goalsgalaxyapi.usecase.model.response.GoalResponseModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalUseCase goalUseCase;
    public GoalController(GoalUseCase goalUseCase) {
        this.goalUseCase = goalUseCase;
    }

    @GetMapping
    public List<Goal> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Goal get(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/{id}/tasks")
    public Goal getTasks(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<GoalResponseModel>> save(@Valid @RequestBody GoalRequestModel request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User userDetails = (User) authentication.getPrincipal();

        ResponseModel<GoalResponseModel> response = goalUseCase.create(request, userDetails.getId());

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
