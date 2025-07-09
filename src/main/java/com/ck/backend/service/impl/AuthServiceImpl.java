package com.ck.backend.service.impl;

import com.ck.backend.config.JwtBlacklist;
import com.ck.backend.config.JwtTokenProvider;
import com.ck.backend.dto.LoginRequestDto;
import com.ck.backend.dto.LoginResponseDto;
import com.ck.backend.dto.SignupRequestDto;
import com.ck.backend.entity.User;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 인증 서비스 구현체
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtBlacklist jwtBlacklist;

    @Override
    public User signup(SignupRequestDto signupRequest) {
        // 사용자 이름 중복 확인
        if (userRepository.findByLoginId(signupRequest.getLoginId()).isPresent()) {
            throw new RuntimeException("사용자 ID가 이미 존재합니다."); // 적절한 예외 처리로 변경 필요
        }
        // 이메일 중복 확인
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new RuntimeException("이메일이 이미 등록되어 있습니다."); // 적절한 예외 처리로 변경 필요
        }

        // 새로운 사용자 엔티티 생성
        User user = new User();
        user.setLoginId(signupRequest.getLoginId());
        user.setEmail(signupRequest.getEmail());
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        String role = signupRequest.getRole();
        if (role == null || role.isEmpty()) {
            user.setRole("USER");
        } else {
            user.setRole(role);
        }

        // 사용자 정보 저장
        return userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        logger.info("Attempting to log in user: {}", loginRequest.getLoginId());

        // 1. 인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );
        logger.info("Authentication successful for user: {}", authentication.getName());

        // 2. 인증 성공 시 JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateToken(authentication);

        // 3. 사용자 정보 조회 (역할을 가져오기 위함)
        User user = userRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")); // 적절한 예외 처리

        // 4. LoginResponseDto 구성 및 반환
        return new LoginResponseDto(accessToken, null, "로그인 성공", user.getRole());
    }

    @Override
    public void logout(String token) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        jwtBlacklist.addToken(actualToken);
        logger.info("Token blacklisted in AuthServiceImpl: {}", actualToken);
    }
}
