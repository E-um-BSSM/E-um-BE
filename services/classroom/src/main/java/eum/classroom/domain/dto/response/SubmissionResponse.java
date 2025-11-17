package eum.classroom.domain.dto.response;

import java.time.LocalDateTime;

public record SubmissionResponse(
        Long submissionId,
        String content,
        String fileUrl,
        LocalDateTime submittedAt,
        String userId,
        LocalDateTime gradedAt,
        Integer score,
        String feedback,
        String status
) {
}
