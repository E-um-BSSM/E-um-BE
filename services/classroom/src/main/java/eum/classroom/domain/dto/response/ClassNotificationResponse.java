package eum.classroom.domain.dto.response;

import java.time.LocalDateTime;

public record ClassNotificationResponse(
        Long notificationId,
        String title,
        String content,
        LocalDateTime createdAt
) {
}
