package eum.transaction.domain.entity;

import eum.transaction.global.constclass.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 트랜잭션 고유 아이디

    @Column(name = "sender_id")
    private String senderId; // 보낸 사람 user_id

    @Column(name = "receiver_id")
    private String receiverId; // 받은 사람 user_id

    @Column(nullable = false)
    private Long amount; // 금액

    @Column(nullable = false)
    private TransactionType type; // 트랜잭션 유형

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 트랜잭션 생성 시간
}
