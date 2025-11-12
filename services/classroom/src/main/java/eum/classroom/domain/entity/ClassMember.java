package eum.classroom.domain.entity;

import eum.classroom.global.constclass.ClassRole;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "class_members")
@IdClass(ClassMemberId.class)
public class ClassMember {
    @Id
    @Column(nullable = false, name = "class_id")
    private Long classId; // 참조 클래스 고유 아이디

    @Id
    @Column(nullable = false, name = "user_id")
    private Long userId; // 참조 유저 고유 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    private ClassRoom classRoom; // 참조 클래스

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user; // 참조 유저 (이거 경로 이상함)

    @Builder.Default
    @Column(nullable = false)
    private ClassRole role = ClassRole.MENTEE; // 클래스안의 유저의 역할

    @CreationTimestamp
    @Column(nullable = false, name = "joined_at")
    private LocalDateTime joinedAt; // 클래스 참여 시간
}
