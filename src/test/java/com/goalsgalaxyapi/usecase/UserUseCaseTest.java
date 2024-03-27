package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void given_aValidUser_whenCreatingUser_thenReturnSuccessTrue() {
        UserRequestModel userRequestModel = new UserRequestModel("user", "user@email.com", "12345");

        when(userRepository.save(any(User.class))).thenReturn(new User(userRequestModel.name(), userRequestModel.email(), userRequestModel.password()));

        ResponseModel<UserResponseModel> result = userUseCase.create(userRequestModel);

        assertTrue(result.success());
        assertNotNull(result.body());
        assertEquals(result.body().email(), userRequestModel.email());
    }

    @Test
    void given_anInvalidUser_whenCreatingUser_thenReturnSuccessFalse() {
        UserRequestModel userRequestModel = new UserRequestModel("", "", "");

        when(userRepository.save(new User(userRequestModel.name(), userRequestModel.email(), userRequestModel.password()))).thenThrow(RuntimeException.class);

        ResponseModel<UserResponseModel> result = userUseCase.create(userRequestModel);

        assertFalse(result.success());
        assertNull(result.body());
    }

    @Test
    void givenUserAlreadyExists_whenCreatingUser_thenErrorMessageIsReturned() {
        UserRequestModel userRequestModel = new UserRequestModel("user", "alreadyexists@email.com", "12345");

        when(userRepository.findByEmail("alreadyexists@email.com")).thenReturn(new User());

        ResponseModel<UserResponseModel> result = userUseCase.create(userRequestModel);

        assertFalse(result.success());
        assertNull(result.body());
    }

}
