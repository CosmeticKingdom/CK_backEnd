package com.ck.backend.service;

import com.ck.backend.dto.NotificationSettingRequestDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.dto.UserProfileUpdateDto;
import com.ck.backend.entity.UserNotificationSetting;

// 사용자 관련 서비스 인터페이스
public interface UserService {
    // 사용자 프로필 조회
    UserProfileDto getUserProfile(Long userId);
    // 사용자 프로필 수정
    UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto);
    // 사용자 계정 삭제
    void deleteUser(Long userId);
    // 알림 설정 업데이트
    UserNotificationSetting updateNotificationSettings(Long userId, NotificationSettingRequestDto settingsDto);
}
