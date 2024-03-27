package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.Token;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.gateway.TokenGateway;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenGateway tokenGateway;

    public UserUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenGateway tokenGateway) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGateway = tokenGateway;
    }

    public Token authenticate(User user) {
        try {
            String token = tokenGateway.generate(user);
            return new Token(token);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseModel<UserResponseModel> create(UserRequestModel request) {
        try {
            if(userRepository.findByEmail(request.email()) != null) {
                return new ResponseModel<>(false, HttpStatus.NOT_FOUND, "Email already exists", null);
            }

            String encryptedPassword = passwordEncoder.encode(request.password());

            User newUser = new User(request.name(), request.email(), encryptedPassword);

            User user = userRepository.save(newUser);

            return new ResponseModel<>(true, HttpStatus.CREATED, null, new UserResponseModel(user.getId(), user.getName(), user.getEmail()));
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    public ResponseModel<UserResponseModel> getUser(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);

            return user.map(value -> new ResponseModel<>(true, HttpStatus.OK, null, new UserResponseModel(value.getId(), value.getName(), value.getEmail())))
                    .orElseGet(() -> new ResponseModel<>(false, HttpStatus.NOT_FOUND, "User not found", null));
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
