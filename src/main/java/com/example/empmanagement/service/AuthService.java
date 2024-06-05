package com.example.empmanagement.service;

import com.example.empmanagement.dto.JwtAuthResponse;
import com.example.empmanagement.dto.LoginRequestDto;
import com.example.empmanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginRequestDto loginRequestDto);
}
