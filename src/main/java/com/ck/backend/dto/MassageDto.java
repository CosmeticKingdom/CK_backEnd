package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MassageDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int durationMinutes;
    // 다른 서비스 관련 필드 추가
}
