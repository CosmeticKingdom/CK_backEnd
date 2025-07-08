package com.ck.backend.service;

import com.ck.backend.dto.ReviewDto;
import com.ck.backend.dto.ReviewRequestDto;

import java.util.List;

// 서비스 후기 관련 서비스 인터페이스
public interface ReviewService {
    // 후기 작성
    ReviewDto createReview(Long userId, Long serviceId, ReviewRequestDto requestDto);
    // 해당 서비스의 모든 후기 조회
    List<ReviewDto> getReviewsByServiceId(Long serviceId);
}
