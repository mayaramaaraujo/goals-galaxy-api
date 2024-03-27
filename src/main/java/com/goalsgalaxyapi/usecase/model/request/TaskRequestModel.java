package com.goalsgalaxyapi.usecase.model.request;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestModel(@NotBlank String name, String description , Long goalId) {
}
