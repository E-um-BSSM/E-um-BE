package eum.user.domain.dto.reponse;

import eum.user.domain.entity.User;
import eum.user.global.constclass.SystemRole;

import java.time.LocalDateTime;

public record SignInResponse (
        String username,
        String equippedBadge,
        SystemRole role,
        String accessToken,
        String refreshToken,
        String expiresIn,
        Long currency,
        LocalDateTime dailyCompensationAt
) {
    public static SignInResponse from(User user) {
        return new SignInResponse(user.getUsername(), user.getEquippedBadge(), user.getRole(), "", "", "", user.getCurrency(), user.getDailyCompensationAt());
    }
}
