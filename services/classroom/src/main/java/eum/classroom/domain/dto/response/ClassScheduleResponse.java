package eum.classroom.domain.dto.response;

import eum.classroom.global.constclass.ClassStatus;

import java.time.LocalDateTime;

public record ClassScheduleResponse(
        Long scheduleId,
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ClassStatus status
) {
}
