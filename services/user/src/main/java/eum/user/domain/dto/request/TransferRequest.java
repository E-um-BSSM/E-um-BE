package eum.user.domain.dto.request;

public record TransferRequest(
        String receiverId,
        Long amount
) {
}
