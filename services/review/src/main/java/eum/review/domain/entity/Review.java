package eum.review.domain.entity;

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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 리뷰 고유 아이디

    @Column(name = "reviewer_id", nullable = false)
    private String reviewerId; // 리뷰 작성자 user_id

    @Column(name = "reviewee_id", nullable = false)
    private String revieweeId; // 리뷰 대상자 user_id

    @Column(name = "class_id", nullable = false)
    private Long classId; // 클래스 고유 아이디

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 리뷰 생성 시간

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 리뷰 수정 시간
}
