package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettingRequestDto {
    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;
    // 다른 알림 유형/선호도 추가
}
