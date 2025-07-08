package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminReservationDto {
    private Long id;
    private Long userId;
    private String userName; // For admin view
    private Long serviceId;
    private String serviceName; // For admin view
    private LocalDateTime reservationTime;
    private String status;
    // 관리자를 위한 기타 관련 예약 세부 정보 추가
}
