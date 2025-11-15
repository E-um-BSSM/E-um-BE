package eum.classroom.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "schedules_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ScheduleUser {
    @EmbeddedId
    private ScheduleUserId id;

    @Column(name = "is_possible", nullable = false)
    private boolean isPossible; // 참석 가능 여부
}
