package eum.classroom.domain.dto.request;

public record SubmissionFeedbackRequest(
        String feedback,
        Integer score
) {
}
