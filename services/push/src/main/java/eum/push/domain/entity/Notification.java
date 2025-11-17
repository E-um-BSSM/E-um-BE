package eum.push.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 알림 고유 아이디

    @Column(name = "body_id", nullable = false)
    private Long bodyId; // 알림 본문 고유 아이디

    @Column(name = "user_id", nullable = false)
    private String userId; // 유저 고유 아이디

    @Builder.Default
    @Column(name = "is_read", nullable = false, columnDefinition = "boolean default false")
    private Boolean isRead = false; // 읽음 여부

    @Column(name = "read_at")
    private LocalDateTime readAt; // 읽은 시간
}
