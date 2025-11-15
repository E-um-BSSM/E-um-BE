package eum.classroom.domain.dto.response;

import java.time.LocalDateTime;

public record AssignmentResponse(
        Long assignmentId,
        String title,
        String description,
        LocalDateTime dueDate,
        Long difficulty,
        LocalDateTime createdAt
) {
}
