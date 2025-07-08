package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Long serviceId;
    private Long userId;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
    // 다른 후기 세부 정보 추가
}
