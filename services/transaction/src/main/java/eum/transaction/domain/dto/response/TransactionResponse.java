package eum.transaction.domain.dto.response;

import eum.transaction.domain.entity.Transaction;
import eum.transaction.global.constclass.TransactionType;

import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        String senderId,
        String receiverId,
        Long amount,
        TransactionType type,
        LocalDateTime createdAt
) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getReceiverId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getCreatedAt()
        );
    }
}
