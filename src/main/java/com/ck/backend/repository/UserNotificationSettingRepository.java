package com.ck.backend.repository;

import com.ck.backend.entity.UserNotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserNotificationSettingRepository extends JpaRepository<UserNotificationSetting, Long> {
    // 사용자 ID로 알림 설정 찾기
}
