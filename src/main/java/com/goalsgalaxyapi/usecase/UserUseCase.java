package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                return new ResponseModel<>(false, "Email already exists", null);
            }

            String encryptedPassword = passwordEncoder.encode(request.password());

            User newUser = new User(request.name(), request.email(), encryptedPassword);

            User user = userRepository.save(newUser);

            return new ResponseModel<>(true, null, new UserResponseModel(user.getId(), user.getName(), user.getEmail()));
        } catch (Exception e) {
            return new ResponseModel<>(false, e.getMessage(), null);
        }
    }
}
