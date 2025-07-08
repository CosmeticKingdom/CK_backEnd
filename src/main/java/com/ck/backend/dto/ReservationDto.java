package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long userId;
    private Long serviceId;
    private LocalDateTime reservationTime;
    private String status; // e.g., PENDING, CONFIRMED, CANCELLED
    // 다른 예약 세부 정보 추가
}
