package eum.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserBadgeId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "badge_id")
    private Long badgeId;
}
