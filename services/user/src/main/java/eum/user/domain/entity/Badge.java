package eum.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 뱃지 고유 아이디

    @Column(nullable = false)
    private String name; // 뱃지 이름 (대답 고수, 대답 전문가, 대답 슈퍼맨, 신입생, 빅 클래스, 부지런!, 슈퍼 부지런!!, 궁금증 박사, 졸업생)

    @Column(columnDefinition = "text")
    private String description; // 뱃지 설명
}
