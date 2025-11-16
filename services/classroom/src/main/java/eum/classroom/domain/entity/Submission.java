package eum.classroom.domain.entity;

import eum.classroom.global.constclass.SubmissionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment_id", nullable = false)
    private Long assignmentId;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "file_url")
    private String fileUrl;

    @CreationTimestamp
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    private Long score;

    @Column(columnDefinition = "text")
    private String feedback;

    @Column(nullable = false)
    private SubmissionStatus status;
}
