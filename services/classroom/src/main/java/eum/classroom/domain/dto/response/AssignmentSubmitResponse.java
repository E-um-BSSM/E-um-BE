package eum.classroom.domain.dto.response;

public record AssignmentSubmitResponse(
        String content,
        String fileUrl,
        String submittedAt
) {
}
