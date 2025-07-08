package com.ck.backend.service.impl;

import com.ck.backend.dto.LoginRequestDto;
import com.ck.backend.dto.LoginResponseDto;
import com.ck.backend.dto.SignupRequestDto;
import com.ck.backend.entity.User;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 인증 서비스 구현체
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(SignupRequestDto signupRequest) {
        // TODO: 회원가입 로직 구현
        // 예시: 사용자 이름 또는 이메일 중복 확인, 비밀번호 암호화, 사용자 저장
        return null; // 임시 반환값
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        // TODO: 로그인 로직 구현
        // 예시: 사용자 인증, JWT 토큰 생성 및 반환
        return null; // 임시 반환값
    }

    @Override
    public void logout(String token) {
        // TODO: 로그아웃 로직 구현
        // 예시: 토큰 무효화 또는 세션 정리
    }
}