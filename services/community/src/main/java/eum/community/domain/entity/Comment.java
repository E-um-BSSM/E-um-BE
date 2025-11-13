package eum.community.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "article_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 댓글 고유 아이디

    @Column(nullable = false)
    private String content; // 댓글 내용

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt; // 댓글 생성 시간

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt; // 댓글 변경 시간

    @Column(nullable = false, name = "created_by")
    private String createdBy; // 댓글 생성 유저 고유 아이디

    @Builder.Default
    @Column(name = "is_accepted", columnDefinition = "bool default false")
    private Boolean isAccepted = false; // Question의 채택 답변 여부

    @Column(name = "thread_id", nullable = false)
    private Long threadId; // 댓글이 속한 쓰레드의 고유 아이디
}
