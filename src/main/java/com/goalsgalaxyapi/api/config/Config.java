package com.goalsgalaxyapi.api.config;

import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.service.GoalService;
import com.goalsgalaxyapi.service.TaskService;
import com.goalsgalaxyapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    TaskRepository taskRepository;
    
    @Bean
    UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    GoalService goalService() {
        return new GoalService(goalRepository);
    }

    @Bean
    TaskService taskService() {
        return new TaskService(taskRepository);
    }

}
