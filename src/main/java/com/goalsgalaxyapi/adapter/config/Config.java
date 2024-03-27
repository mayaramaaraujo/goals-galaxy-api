package com.goalsgalaxyapi.adapter.config;

import com.goalsgalaxyapi.adapter.config.security.SecurityFilter;
import com.goalsgalaxyapi.adapter.provider.AuthorizationDataProvider;
import com.goalsgalaxyapi.adapter.provider.TokenJWTProvider;
import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.GoalUseCase;
import com.goalsgalaxyapi.usecase.TaskUseCase;
import com.goalsgalaxyapi.usecase.UserUseCase;
import com.goalsgalaxyapi.usecase.gateway.SecurityFilterGateway;
import com.goalsgalaxyapi.usecase.gateway.TokenGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {

    @Value("${api.security.token.secret}")
    private String secret;

    UserRepository userRepository;
    GoalRepository goalRepository;
    TaskRepository taskRepository;

    public Config(UserRepository userRepository, GoalRepository goalRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.taskRepository = taskRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserUseCase userUseCase() {
        return new UserUseCase(userRepository, passwordEncoder(), tokenGateway());
    }

    @Bean
    GoalUseCase goalUseCase() {
        return new GoalUseCase(goalRepository, userRepository);
    }

    @Bean
    TaskUseCase taskUseCase() {
        return new TaskUseCase(taskRepository, goalRepository, userUseCase(), goalUseCase());
    }

    @Bean
    TokenGateway tokenGateway() {
        return new TokenJWTProvider(secret);
    }

    @Bean
    SecurityFilterGateway securityFilterGateway() {
        return new SecurityFilter(tokenGateway(), userRepository);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new AuthorizationDataProvider(userRepository);
    }

}
