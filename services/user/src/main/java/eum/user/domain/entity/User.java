package eum.user.domain.entity;

import eum.user.global.constclass.SystemRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false, name = "user_id")
    private String userId; // 유저 고유 아이디

    @Column(nullable = false)
    private String username; // 유저 별명

    @Column(nullable = false)
    private String email; // 유저 이메일

    @Column(nullable = false)
    private String password; // 비밀번호 (보안!)

    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 1000")
    private Long strength = (Long)1000L; // 내공

    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Long currency = (Long)0L; // 계정 재산

    @Column(name = "equipped_badge")
    private String equippedBadge; // 장착중인 뱃지

    @Column(nullable = false, name = "system_role")
    private SystemRole role; // 시스템의 계정 역할

    @Column(name = "bsm_id")
    private String bsmId; // 추후에 bsmOauth연동

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 계정 생성일

    @Column(name = "daily_compensation_at")
    private LocalDateTime dailyCompensationAt; // 일일 출석 보상 시간
}
