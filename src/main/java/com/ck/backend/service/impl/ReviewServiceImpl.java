package com.ck.backend.service.impl;

import com.ck.backend.dto.ReviewDto;
import com.ck.backend.dto.ReviewRequestDto;
import com.ck.backend.entity.Review;
import com.ck.backend.entity.User;
import com.ck.backend.repository.ReviewRepository;
import com.ck.backend.repository.MassageRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 서비스 후기 서비스 구현체
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MassageRepository massageRepository;

    @Override
    public ReviewDto createReview(Long userId, Long serviceId, ReviewRequestDto requestDto) {
        // TODO: 후기 작성 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public List<ReviewDto> getReviewsByServiceId(Long serviceId) {
        // TODO: 해당 서비스의 모든 후기 조회 로직 구현
        return null; // 임시 반환값
    }
}