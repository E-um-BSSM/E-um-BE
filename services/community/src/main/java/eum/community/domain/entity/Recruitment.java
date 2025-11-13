package eum.community.domain.entity;

import eum.classroom.domain.entity.ClassRoom;
import eum.community.global.constclass.RecruitmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "recruitments")
@DiscriminatorValue("RECRUITMENT")
public class Recruitment extends BaseThread {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    private ClassRoom classRoom;

    @Column(name = "max_participants")
    private Long maxParticipants;

    @Column(name = "goal_participants")
    private Long goalParticipants;

    @Builder.Default
    @Column(name = "current_participants", nullable = false, columnDefinition = "integer default 0")
    private Long currentParticipants = (Long)0L;

    @Builder.Default
    @Column(nullable = false)
    private RecruitmentStatus recruitmentStatus = RecruitmentStatus.OPEN;

    private LocalDateTime deadline;
}
