package eum.user.domain.dto.reponse;

import java.time.LocalDateTime;

public record ProfileResponse(
        String username,
        String color,
        String border,
        String background,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
