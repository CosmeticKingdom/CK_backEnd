package com.ck.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "user_notification_setting")
@AttributeOverride(name = "id", column = @Column(name = "SETTING_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserNotificationSetting extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @Column(name = "PUSH_ENABLED")
    private boolean pushEnabled;
    @Column(name = "EMAIL_ENABLED")
    private boolean emailEnabled;
    @Column(name = "SMS_ENABLED")
    private boolean smsEnabled; // SMS 알림 활성화 여부
    // Add other notification types/preferences
}
