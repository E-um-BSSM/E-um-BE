package eum.classroom.domain.dto.request;

import eum.classroom.global.constclass.ClassStatus;

import java.util.List;

public record ClassRequest(
        String title,
        String description,
        Long difficulty,
        List<String> tags,
        ClassStatus status
) {
}
