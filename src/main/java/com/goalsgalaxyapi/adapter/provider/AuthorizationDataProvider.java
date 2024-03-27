package com.goalsgalaxyapi.adapter.provider;

import com.goalsgalaxyapi.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthorizationDataProvider implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthorizationDataProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
