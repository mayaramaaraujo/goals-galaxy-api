package com.goalsgalaxyapi.api.controller;

import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.repository.GoalRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalRepository goalRepository;
    public GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @GetMapping
    public List<Goal> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Goal get(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public Goal save(@Valid @RequestBody Goal goal) {
        return null;
    }

    @PatchMapping("/{id}")
    public Goal update(@PathVariable Long id, @RequestBody Goal goal) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

}
