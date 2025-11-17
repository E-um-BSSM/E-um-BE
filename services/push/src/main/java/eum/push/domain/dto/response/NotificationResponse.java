package eum.push.domain.dto.response;

import eum.push.domain.entity.Notification;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        Long bodyId,
        String userId,
        Boolean isRead,
        LocalDateTime readAt
) {
    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getBodyId(),
                notification.getUserId(),
                notification.getIsRead(),
                notification.getReadAt()
        );
    }
}
