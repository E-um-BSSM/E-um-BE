package eum.user.domain.dto.response;

import eum.user.domain.entity.Badge;

public record BadgeResponse(
        Long id,
        String name,
        String description
) {
    public static BadgeResponse from(Badge badge) {
        return new BadgeResponse(
                badge.getId(),
                badge.getName(),
                badge.getDescription()
        );
    }
}
