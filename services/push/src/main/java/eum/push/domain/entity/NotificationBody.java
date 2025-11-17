package eum.push.domain.entity;

import eum.push.global.constclass.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "notification_body")
public class NotificationBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 알림 본문 고유 아이디

    @Column(nullable = false)
    private NotificationType type; // 알림 유형

    @Column(nullable = false)
    private String title; // 알림 제목

    @Column(nullable = false, columnDefinition = "text")
    private String body; // 알림 내용

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 알림 생성 시간
}
