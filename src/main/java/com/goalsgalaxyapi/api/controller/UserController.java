package com.goalsgalaxyapi.api.controller;

import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.usecase.UserUseCase;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userService;

    public UserController(UserUseCase userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
       return null;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<UserResponseModel>> save(@Valid @RequestBody UserRequestModel request) {
        ResponseModel<UserResponseModel> response = userService.create(request);

        if (!response.success()) {
            return ResponseEntity.status(400).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
