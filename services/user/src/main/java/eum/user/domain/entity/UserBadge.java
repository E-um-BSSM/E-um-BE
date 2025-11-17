package eum.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user_badges")
public class UserBadge {
    @EmbeddedId
    private UserBadgeId id;

    @CreationTimestamp
    @Column(name = "earned_at", nullable = false)
    private LocalDateTime earnedAt; // 뱃지 획득 시간
}
