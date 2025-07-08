package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDto {
    private Long totalUsers;
    private Long totalReservations;
    private Double totalRevenue;
    private Map<String, Long> userRegistrationTrend; // e.g., daily/monthly counts
    private Map<String, Long> reservationStatusCounts; // e.g., CONFIRMED: 100, PENDING: 20
    // 기타 관련 통계 추가
}
