package eum.classroom.domain.dto.request;

import eum.classroom.global.constclass.ClassStatus;

import java.util.List;

public record ClassSearchRequest(
        Long difficulty,
        List<String> tags,
        ClassStatus status
) {
}
