package com.ck.backend.service.impl;

import com.ck.backend.dto.ReviewDto;
import com.ck.backend.dto.ReviewRequestDto;
import com.ck.backend.entity.Massage;
import com.ck.backend.entity.Reservation;
import com.ck.backend.entity.Review;
import com.ck.backend.entity.User;
import com.ck.backend.repository.ReservationRepository;
import com.ck.backend.repository.ReviewRepository;
import com.ck.backend.repository.MassageRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 서비스 후기 서비스 구현체
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MassageRepository massageRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReviewDto createReview(Long userId, Long serviceId, ReviewRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Massage massage = massageRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Massage not found"));

        // 사용자가 해당 서비스를 예약했고, 예약 상태가 CONFIRMED이며, 마사지 시간이 지났는지 확인
        List<Reservation> reservations = reservationRepository.findByUserIdAndMassageId(userId, serviceId);
        boolean canReview = reservations.stream()
                .anyMatch(reservation -> "CONFIRMED".equals(reservation.getStatus()) && reservation.getReservationTime().isBefore(LocalDateTime.now()));

        if (!canReview) {
            throw new IllegalStateException("You can only review after the confirmed massage session is over.");
        }

        Review review = Review.builder()
                .user(user)
                .massage(massage)
                .comment(requestDto.getComment())
                .rating(requestDto.getRating())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewDto(
                savedReview.getId(),
                savedReview.getMassage().getId(),
                savedReview.getUser().getId(),
                savedReview.getComment(),
                savedReview.getRating().getValue(),
                savedReview.getCreatedAt()
        );
    }

    @Override
    public List<ReviewDto> getReviewsByServiceId(Long serviceId) {
        List<Review> reviews = reviewRepository.findByMassageId(serviceId);
        return reviews.stream()
                .map(review -> new ReviewDto(
                        review.getId(),
                        review.getMassage().getId(),
                        review.getUser().getId(),
                        review.getComment(),
                        review.getRating().getValue(),
                        review.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
