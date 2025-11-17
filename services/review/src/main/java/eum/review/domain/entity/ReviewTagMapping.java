package eum.review.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "review_tag_mappings")
public class ReviewTagMapping {
    @EmbeddedId
    private ReviewTagMappingId id;
}
