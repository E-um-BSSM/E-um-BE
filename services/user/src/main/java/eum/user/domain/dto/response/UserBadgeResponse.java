package eum.user.domain.dto.response;

import eum.user.domain.entity.UserBadge;

import java.time.LocalDateTime;

public record UserBadgeResponse(
        String userId,
        Long badgeId,
        LocalDateTime earnedAt
) {
    public static UserBadgeResponse from(UserBadge userBadge) {
        return new UserBadgeResponse(
                userBadge.getId().getUserId(),
                userBadge.getId().getBadgeId(),
                userBadge.getEarnedAt()
        );
    }
}
