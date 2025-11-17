package eum.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_badges")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBadge {

    @EmbeddedId
    private UserBadgeId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("badgeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Badge badge;

    @CreationTimestamp
    @Column(name = "earned_at", updatable = false)
    private LocalDateTime earnedAt;
}
