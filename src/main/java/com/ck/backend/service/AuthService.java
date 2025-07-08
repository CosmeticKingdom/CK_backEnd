package com.ck.backend.service;

import com.ck.backend.dto.LoginRequestDto;
import com.ck.backend.dto.LoginResponseDto;
import com.ck.backend.dto.SignupRequestDto;
import com.ck.backend.entity.User;

// 인증 관련 서비스 인터페이스
public interface AuthService {
    // 회원가입 처리
    User signup(SignupRequestDto signupRequest);
    // 로그인 처리
    LoginResponseDto login(LoginRequestDto loginRequest);
    // 로그아웃 처리
    void logout(String token);
}
