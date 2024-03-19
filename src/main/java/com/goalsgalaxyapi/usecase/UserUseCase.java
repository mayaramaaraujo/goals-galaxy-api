package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserUseCase {

    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    public UserUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseModel<UserResponseModel> create(UserRequestModel request) {
        try {
            if(userRepository.findByEmail(request.email()).isPresent()) {
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

            if(user.isEmpty()) {
                return new ResponseModel<>(false, HttpStatus.NOT_FOUND, "User not found", null);
            }

            return new ResponseModel<>(true, HttpStatus.OK, null, new UserResponseModel(user.get().getId(), user.get().getName(), user.get().getEmail()));
        } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
