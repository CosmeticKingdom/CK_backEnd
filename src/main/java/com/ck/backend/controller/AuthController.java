package com.ck.backend.controller;

import com.ck.backend.dto.LoginRequestDto;
import com.ck.backend.dto.LoginResponseDto;
import com.ck.backend.dto.SignupRequestDto;
import com.ck.backend.entity.User;
import com.ck.backend.service.AuthService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequestDto signupRequest) {
        User newUser = authService.signup(signupRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(name = "Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
}
