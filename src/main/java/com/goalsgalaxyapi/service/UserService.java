package com.goalsgalaxyapi.service;

import com.goalsgalaxyapi.domain.repository.UserRepository;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
