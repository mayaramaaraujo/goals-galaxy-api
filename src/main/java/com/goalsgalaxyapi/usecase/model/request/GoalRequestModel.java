package com.goalsgalaxyapi.usecase.model.request;

import jakarta.validation.constraints.NotBlank;

public record GoalRequestModel(@NotBlank String name, String description, String deadline, String category) {
}
