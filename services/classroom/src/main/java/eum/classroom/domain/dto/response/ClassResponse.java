package eum.classroom.domain.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ClassResponse(
        String classId,
        String title,
        String classCode,
        String description,
        List<String> tags,
        Long difficulty,
        LocalDateTime createdAt
) {
}
