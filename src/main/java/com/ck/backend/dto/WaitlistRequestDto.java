package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitlistRequestDto {
    private Long serviceId;
    // 대기자 명단을 위한 기타 관련 필드 추가
}
