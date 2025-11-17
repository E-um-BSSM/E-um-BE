package eum.item.domain.dto.response;

import eum.item.domain.entity.Profile;

import java.time.LocalDateTime;

public record ProfileResponse(
        String userId,
        String color,
        String border,
        String background,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getUserId(),
                profile.getColor(),
                profile.getBorder(),
                profile.getBackground(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
