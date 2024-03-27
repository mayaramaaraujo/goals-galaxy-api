package com.goalsgalaxyapi.adapter.entrypoint;

import com.goalsgalaxyapi.domain.model.Token;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.usecase.UserUseCase;
import com.goalsgalaxyapi.usecase.model.request.UserLoginRequestModel;
import com.goalsgalaxyapi.usecase.model.request.UserRequestModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.usecase.model.response.UserResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserUseCase userUseCase;

    public UserController(AuthenticationManager authenticationManager, UserUseCase userUseCase) {
        this.authenticationManager = authenticationManager;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginRequestModel request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(token);

        Token tokenJWT = userUseCase.authenticate((User) authentication.getPrincipal());

        if(tokenJWT.token().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(tokenJWT);
    }

    @GetMapping
    public List<User> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<UserResponseModel>> get(@PathVariable Long id) {
        ResponseModel<UserResponseModel> response = userUseCase.getUser(id);

        if (!response.success()) {
            return ResponseEntity.status(response.code()).body(response);
        }

        return ResponseEntity.status(response.code()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<UserResponseModel>> save(@Valid @RequestBody UserRequestModel request) {
        ResponseModel<UserResponseModel> response = userUseCase.create(request);

        if (!response.success()) {
            return ResponseEntity.status(response.code()).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
