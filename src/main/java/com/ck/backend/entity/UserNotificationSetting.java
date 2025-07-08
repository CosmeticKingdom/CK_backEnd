package com.ck.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_notification_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled; // SMS 알림 활성화 여부
    // Add other notification types/preferences
}
