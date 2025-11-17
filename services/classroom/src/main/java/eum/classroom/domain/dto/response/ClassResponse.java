package eum.classroom.domain.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ClassResponse(
        Long classId,
        String title,
        Long classCode,
        String description,
        List<String> tags,
        Long difficulty,
        LocalDateTime createdAt
) {
}
