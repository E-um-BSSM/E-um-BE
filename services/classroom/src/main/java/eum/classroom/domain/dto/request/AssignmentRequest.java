package eum.classroom.domain.dto.request;

import java.time.LocalDateTime;

public record AssignmentRequest(
        String title,
        String description,
        LocalDateTime dueDate,
        Long difficulty
) {
}
