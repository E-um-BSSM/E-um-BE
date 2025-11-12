package eum.classroom.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "class_tags")
public class ClassTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 클래스 태그의 고유 아이디

    @Column(nullable = false, name = "class_id")
    private Long classId; // 참조 클래스 고유 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    private ClassRoom classRoom; // 참조 클래스

    @Column(nullable = false)
    private String content; // 클래스 태그의 내용
}
