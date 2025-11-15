package eum.classroom.domain.entity;

import eum.classroom.global.constclass.AccessScope;
import eum.classroom.global.constclass.ClassStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "classrooms")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true, name = "classroom_id")
    private Long classRoomId; // 클래스 고유 아이디

    @Column(nullable = false)
    private String title; // 클래스 제목

    @Column(nullable = false)
    private String description; // 클래스 부가설명

    @Column(name = "classroom_code")
    private Long classRoomCode; // 클래스 초대코드 (만료 있음!)

    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Long difficultyLevel = (Long)0L; // 클래스 난이도 [0은 측정안됨]

    @Builder.Default
    @Column(nullable = false, name = "access_scope")
    private AccessScope accessScope = AccessScope.PRIVATE; // 클래스 공개 범위

    @Builder.Default
    @Column(nullable = false, name = "class_status")
    private ClassStatus classStatus = ClassStatus.ARCHIVED; // 클래스 현재 상태

    @Column(nullable = false, name = "created_by")
    private String createdBy; // 클래스 만든 사용자 고유 아이디

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt; // 클래스 생성 시간

    @Builder.Default
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassTag> classTags = new ArrayList<>(); // 클래스 태그와의 매핑

    @Builder.Default
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassMember> classMembers = new ArrayList<>(); // 클래스 맴버와의 매핑
}
