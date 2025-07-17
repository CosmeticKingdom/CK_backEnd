package com.ck.backend.dto;

import com.ck.backend.util.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private String comment;
    private Rating rating; // e.g., 1-5 stars
    // 다른 후기 관련 필드 추가
}
