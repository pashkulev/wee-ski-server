package com.vankata.weeski.service;

import com.vankata.weeski.payload.ApiResponse;
import com.vankata.weeski.payload.JwtAuthenticationResponse;
import com.vankata.weeski.payload.LoginRequest;
import com.vankata.weeski.payload.RegisterRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AuthenticationService {

    JwtAuthenticationResponse login(LoginRequest loginRequest);

    ApiResponse register(RegisterRequest registerRequest, MultipartFile imageFile);
}
