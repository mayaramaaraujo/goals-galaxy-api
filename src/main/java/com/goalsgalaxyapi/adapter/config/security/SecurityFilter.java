package com.goalsgalaxyapi.adapter.config.security;

import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.gateway.SecurityFilterGateway;
import com.goalsgalaxyapi.usecase.gateway.TokenGateway;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter implements SecurityFilterGateway {

    private final TokenGateway tokenGateway;
    private final UserRepository userRepository;

    public SecurityFilter(TokenGateway tokenGateway, UserRepository userRepository) {
        this.tokenGateway = tokenGateway;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = retrieveToken(request);

        if (tokenJWT != null) {
            String subject = tokenGateway.verify(tokenJWT);
            User user = (User) userRepository.findByEmail(subject);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    public String retrieveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
