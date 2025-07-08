package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    private Long serviceId;
    private LocalDateTime reservationTime;
    // 예약을 위한 기타 필수 필드 추가
}
