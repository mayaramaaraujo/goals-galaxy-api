package com.goalsgalaxyapi.usecase.gateway;

import com.goalsgalaxyapi.domain.model.User;

public interface TokenGateway {
    String generate(User user);
    String verify(String token);
}
