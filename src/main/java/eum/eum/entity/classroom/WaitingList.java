package eum.eum.entity.classroom;

import eum.eum.entity.certification.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "waiting_list")
@NoArgsConstructor
@AllArgsConstructor
public class WaitingList {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity classId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @Column(name = "join_at")
    private LocalDateTime joinAt = LocalDateTime.now();
}
