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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return new UserProfileDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getRole());
    }

    @Override
    public UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (updateDto.getName() != null) {
            user.setName(updateDto.getName());
        }
        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }
        if (updateDto.getPhoneNumber() != null) {
            user.setPhoneNumber(updateDto.getPhoneNumber());
        }

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getPhoneNumber(), updatedUser.getEmail(), updatedUser.getRole());
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserNotificationSetting updateNotificationSettings(Long userId, NotificationSettingRequestDto settingsDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        UserNotificationSetting setting = notificationSettingRepository.findByUser(user)
                .orElseGet(() -> UserNotificationSetting.builder().user(user).build());

        if (settingsDto.getEmailEnabled() != null) {
            setting.setEmailEnabled(settingsDto.getEmailEnabled());
        }
        if (settingsDto.getSmsEnabled() != null) {
            setting.setSmsEnabled(settingsDto.getSmsEnabled());
        }
        if (settingsDto.getPushEnabled() != null) {
            setting.setPushEnabled(settingsDto.getPushEnabled());
        }

        return notificationSettingRepository.save(setting);
    }
}