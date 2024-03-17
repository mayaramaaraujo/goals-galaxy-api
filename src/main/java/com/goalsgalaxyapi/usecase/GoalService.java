package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.repository.GoalRepository;

public class GoalService {
    GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
}
