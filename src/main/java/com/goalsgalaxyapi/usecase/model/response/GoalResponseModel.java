package com.goalsgalaxyapi.usecase.model.response;

import com.goalsgalaxyapi.domain.model.Category;
import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.utils.DateFormatter;

import java.util.List;

public record GoalResponseModel(Long id, String name, String description, String createdDate, String deadline, Category category, List<Task> tasks, Long userId) {
    public GoalResponseModel(Goal goal) {
        this(goal.getId(), goal.getName(), goal.getDescription(), DateFormatter.localDateTimeToString(goal.getCreatedDate()), DateFormatter.localDateTimeToString(goal.getDeadline()), goal.getCategory(), goal.getTasks(), goal.getUser().getId());
    }
}
