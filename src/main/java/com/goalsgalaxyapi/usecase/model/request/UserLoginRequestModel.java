package com.goalsgalaxyapi.usecase.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record UserLoginRequestModel(@NotBlank @Email String email, @NotBlank @Size(max = 20) String password) {
}
