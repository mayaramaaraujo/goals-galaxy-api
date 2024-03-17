package com.goalsgalaxyapi.api.config;

import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.GoalUseCase;
import com.goalsgalaxyapi.usecase.TaskService;
import com.goalsgalaxyapi.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Bean
    UserUseCase userUseCase() {
        return new UserUseCase(userRepository, passwordEncoder);
    }

    @Bean
    GoalUseCase goalService() {
        return new GoalUseCase(goalRepository, userRepository);
    }

    @Bean
    TaskService taskService() {
        return new TaskService(taskRepository);
    }

}
