package eum.classroom.domain.dto.response;

import eum.classroom.global.constclass.ClassStatus;
import eum.classroom.global.constclass.SubmissionStatus;

import java.time.LocalDateTime;

public record AssignmentSearchResponse(
        Long assignmentId,
        String title,
        String description,
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        SubmissionStatus status
) {
}
