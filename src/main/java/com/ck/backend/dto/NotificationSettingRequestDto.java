package com.ck.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettingRequestDto {
    private Boolean emailEnabled;
    private Boolean smsEnabled;
    private Boolean pushEnabled;
}