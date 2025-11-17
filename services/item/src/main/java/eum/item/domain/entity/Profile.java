package eum.item.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "user_id", nullable = false, updatable = false, unique = true)
    private String userId; // 유저 고유 아이디

    @Column
    private String color; // 프로필 색상

    @Column
    private String border; // 프로필 테두리

    @Column
    private String background; // 프로필 배경

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 프로필 생성 시간

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 프로필 수정 시간
}
