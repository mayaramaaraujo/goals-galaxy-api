package com.goalsgalaxyapi.usecase.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseModel<T>(@JsonIgnore boolean success, String message, @JsonUnwrapped T body) {
}