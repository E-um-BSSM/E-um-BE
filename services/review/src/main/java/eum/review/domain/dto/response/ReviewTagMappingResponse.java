package eum.review.domain.dto.response;

import eum.review.domain.entity.ReviewTagMapping;

public record ReviewTagMappingResponse(
        Long reviewId,
        Long tagId
) {
    public static ReviewTagMappingResponse from(ReviewTagMapping reviewTagMapping) {
        return new ReviewTagMappingResponse(
                reviewTagMapping.getId().getReviewId(),
                reviewTagMapping.getId().getTagId()
        );
    }
}
