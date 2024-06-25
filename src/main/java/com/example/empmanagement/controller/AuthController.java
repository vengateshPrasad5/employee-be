package com.example.empmanagement.controller;

import com.example.empmanagement.dto.JwtAuthResponse;
import com.example.empmanagement.dto.LoginRequestDto;
import com.example.empmanagement.dto.RegisterDto;
import com.example.empmanagement.exception.ResourceNotFoundException;
import com.example.empmanagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto requestDto) {
        try {
            JwtAuthResponse jwtAuthResponse = authService.login(requestDto);

            return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException("User Not found");
        }
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);

        try {
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
