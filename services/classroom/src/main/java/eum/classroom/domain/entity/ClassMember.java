package eum.classroom.domain.entity;

import eum.classroom.global.constclass.ClassRole;
import jakarta.persistence.*;
import lombok.*;
import eum.user.domain.entity.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "class_members")
public class ClassMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 클래스 맴버 고유 아이디

    @Column(name = "class_id", nullable = false)
    private Long classId; // 참조 클래스 고유 아이디

    @Column(name = "user_id", nullable = false)
    private Long userId; // 참조 유저 고유 아이디

    @Builder.Default
    @Column(nullable = false)
    private ClassRole role = ClassRole.MENTEE; // 클래스안의 유저의 역할

    @CreationTimestamp
    @Column(nullable = false, name = "joined_at")
    private LocalDateTime joinedAt; // 클래스 참여 시간
}
