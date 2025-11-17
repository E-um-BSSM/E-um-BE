package eum.push.domain.dto.response;

import eum.push.domain.entity.NotificationBody;
import eum.push.global.constclass.NotificationType;

import java.time.LocalDateTime;

public record NotificationBodyResponse(
        Long id,
        NotificationType type,
        String title,
        String body,
        LocalDateTime createdAt
) {
    public static NotificationBodyResponse from(NotificationBody notificationBody) {
        return new NotificationBodyResponse(
                notificationBody.getId(),
                notificationBody.getType(),
                notificationBody.getTitle(),
                notificationBody.getBody(),
                notificationBody.getCreatedAt()
        );
    }
}
