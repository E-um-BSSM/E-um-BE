package eum.community.domain.entity;

import eum.community.global.constclass.ArticleStatus;
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
@Table(name = "articles")
@DiscriminatorValue("ARTICLE")
public class Article extends BaseThread {

    @Builder.Default
    @Column(nullable = false)
    private ArticleStatus status = ArticleStatus.DRAFT; // 게시글 상태

    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0", name = "view_count")
    private Long viewCount = (Long)0L; // 조회수
}
