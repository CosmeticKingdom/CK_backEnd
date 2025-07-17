package com.ck.backend.controller;

import com.ck.backend.dto.ReviewDto;
import com.ck.backend.dto.ReviewRequestDto;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.ReviewService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/{serviceId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserRepository userRepository;

    private Long getUserIdFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    @PostMapping
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
