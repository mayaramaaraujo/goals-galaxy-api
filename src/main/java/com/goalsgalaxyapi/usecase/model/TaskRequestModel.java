package com.goalsgalaxyapi.usecase.model;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestModel(@NotBlank String name, String description) {
}
