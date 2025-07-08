package com.ck.backend.controller;

import com.ck.backend.dto.ReviewDto;
import com.ck.backend.dto.ReviewRequestDto;
import com.ck.backend.service.ReviewService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/{serviceId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 실제 애플리케이션에서는 보안 컨텍스트에서 사용자 ID를 추출해야 합니다.
    // 현재는 플레이스홀더 userId를 사용합니다.
    private Long getUserIdFromContext() {
        // 인증된 사용자의 ID를 가져오는 실제 로직으로 대체되어야 합니다.
        return 1L; 
    }

    @PostMapping("/")
    public ResponseEntity<ReviewDto> createReview(@PathVariable Long serviceId, @RequestBody ReviewRequestDto requestDto) {
        Long userId = getUserIdFromContext();
        ReviewDto newReview = reviewService.createReview(userId, serviceId, requestDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ReviewDto>> getReviewsByServiceId(@PathVariable Long serviceId) {
        List<ReviewDto> reviews = reviewService.getReviewsByServiceId(serviceId);
        return ResponseEntity.ok(reviews);
    }
}
