package eum.community.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Getter
@Table(name = "threads")
public abstract class BaseThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 쓰레드 고유 아이디

    @Column(nullable = false)
    private String title; // 쓰레드 제목

    @Column(nullable = false)
    private String content; // 쓰레디 내용

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt; // 쓰레드 생성 시간

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt; // 쓰레드 변경 시간

    @Column(nullable = false, name = "created_by")
    private String createdBy; // 쓰레드 생성 유저 고유 아이디
}
