package eum.user.domain.dto.reponse;

import eum.user.domain.entity.User;
import eum.user.global.constclass.SystemRole;

import java.time.LocalDateTime;

public record SignUpResponse(
        String username,
        String email,
        String equippedBadge,
        SystemRole role,
        Long strength,
        Long currency,
        LocalDateTime createdAt,
        LocalDateTime dailyCompensationAt
) {
    public static SignUpResponse from(User user) {
        return new SignUpResponse(user.getUsername(), user.getEmail(), user.getEquippedBadge(), user.getRole(), user.getStrength(), user.getCurrency(), user.getCreatedAt(), user.getDailyCompensationAt());
    }
}
