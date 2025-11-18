package eum.user.domain.dto.reponse;

import java.time.LocalDateTime;

public record BadgeResponse(
        String badgeName,
        String badgeDescription,
        LocalDateTime earnAt
) {
}
