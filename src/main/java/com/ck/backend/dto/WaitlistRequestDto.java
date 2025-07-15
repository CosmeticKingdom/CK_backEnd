package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitlistRequestDto {
    private Long serviceId;
    private LocalDateTime desiredTime;
    // 대기자 명단을 위한 기타 관련 필드 추가
}
