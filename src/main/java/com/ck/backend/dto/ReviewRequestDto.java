package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private String comment;
    private int rating; // e.g., 1-5 stars
    // 다른 후기 관련 필드 추가
}
