package com.ck.backend.service.impl;

import com.ck.backend.dto.NotificationSettingRequestDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.dto.UserProfileUpdateDto;
import com.ck.backend.entity.User;
import com.ck.backend.entity.UserNotificationSetting;
import com.ck.backend.repository.UserNotificationSettingRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 사용자 서비스 구현체
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserNotificationSettingRepository notificationSettingRepository;

    @Override
    public UserProfileDto getUserProfile(Long userId) {
        // TODO: 사용자 프로필 조회 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto) {
        // TODO: 사용자 프로필 수정 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO: 사용자 계정 삭제 로직 구현
    }

    @Override
    public UserNotificationSetting updateNotificationSettings(Long userId, NotificationSettingRequestDto settingsDto) {
        // TODO: 알림 설정 업데이트 로직 구현
        return null; // 임시 반환값
    }
}