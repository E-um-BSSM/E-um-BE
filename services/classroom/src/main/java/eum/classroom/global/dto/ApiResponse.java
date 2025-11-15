package eum.classroom.global.dto;

public record ApiResponse<T>(
        boolean success,
        T data
) {
}
