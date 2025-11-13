package eum.community.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@Table(name = "questions")
@DiscriminatorValue("QUESTION")
public class Question extends BaseThread {

    @Builder.Default
    @Column(name = "is_resolved", columnDefinition = "bool default false", nullable = false)
    private Boolean isResolved = false;

    @Column(name = "accepted_answer_id")
    private Long acceptedAnswerId;

    @Builder.Default
    @Column(name = "bounty_points", columnDefinition = "integer default 0")
    private Long bountyPoints = (Long)0L;

    @Builder.Default
    @Column(name = "view_count", columnDefinition = "integer default 0")
    private Long viewCount = (Long)0L;


}
