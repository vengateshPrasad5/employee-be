package com.example.empmanagement.security;

import com.example.empmanagement.entity.User;
import com.example.empmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (isValidUser(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new AuthenticationException("Invalid credentials") {
            };
        }
    }

    private boolean isValidUser(String username, String password) {
        User user = userRepository.findByUsername(username).get();
        return user.getUsername().equals(username) &&
                passwordEncoder.matches(password, user.getPassword());
    }
}
