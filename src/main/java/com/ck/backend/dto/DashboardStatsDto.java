package com.ck.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDto {
    private long totalUsers;
    private long totalReservations;
    private double totalRevenue;
    private double dailyRevenue;
    private double monthlyRevenue;
    // 필요에 따라 다른 통계 지표 추가
}