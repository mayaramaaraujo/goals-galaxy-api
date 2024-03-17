package com.goalsgalaxyapi.usecase.model.response;

import com.goalsgalaxyapi.domain.model.Task;

import java.util.List;

public record GoalResponseModel(Long id, String name, String description, String createdDate, String deadline,
                                com.goalsgalaxyapi.domain.model.Category category, List<Task> tasks, Long userId) {
}
