package eum.review.domain.dto.response;

import eum.review.domain.entity.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String reviewerId,
        String revieweeId,
        Long classId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getReviewerId(),
                review.getRevieweeId(),
                review.getClassId(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
