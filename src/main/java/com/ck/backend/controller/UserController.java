package com.ck.backend.controller;

import com.ck.backend.dto.NotificationSettingRequestDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.dto.UserProfileUpdateDto;
import com.ck.backend.entity.UserNotificationSetting;
import com.ck.backend.service.UserService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 실제 애플리케이션에서는 보안 컨텍스트에서 사용자 ID를 추출해야 합니다.
    // 현재는 플레이스홀더 userId를 사용합니다.
    private Long getUserIdFromContext() {
        // 인증된 사용자의 ID를 가져오는 실제 로직으로 대체되어야 합니다.
        return 1L; 
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile() {
        Long userId = getUserIdFromContext();
        UserProfileDto profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody UserProfileUpdateDto updateDto) {
        Long userId = getUserIdFromContext();
        UserProfileDto updatedProfile = userService.updateUserProfile(userId, updateDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteUser() {
        Long userId = getUserIdFromContext();
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<UserNotificationSetting> updateNotificationSettings(@RequestBody NotificationSettingRequestDto settingsDto) {
        Long userId = getUserIdFromContext();
        UserNotificationSetting updatedSettings = userService.updateNotificationSettings(userId, settingsDto);
        return ResponseEntity.ok(updatedSettings);
    }
}
