package com.ck.backend.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "user_notification_setting")
@AttributeOverride(name = "id", column = @Column(name = "NOTIFICATION_SETTING_ID"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserNotificationSetting extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private User user;

    @Column(name = "EMAIL_ENABLED", nullable = false)
    private boolean emailEnabled = true;

    @Column(name = "SMS_ENABLED", nullable = false)
    private boolean smsEnabled = true;

    @Column(name = "PUSH_ENABLED", nullable = false)
    private boolean pushEnabled = true;
}