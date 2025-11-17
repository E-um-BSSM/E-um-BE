package eum.review.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false, updatable = false, unique = true)
    private Long tagId; // 태그 고유 아이디

    @Column(nullable = false)
    private String name; // 태그 이름 (열정적, 성실한, 철저한, 계획적, 깔끔한, 구체적, 활발한, 책임적인, 믿음직한, 강인한, 겸손한, 귀여운, 리더십있는, 느긋한, 기특한, 낭만적인, 단호한, 솔직한, 다정한, 털털한, 신중한)
}
