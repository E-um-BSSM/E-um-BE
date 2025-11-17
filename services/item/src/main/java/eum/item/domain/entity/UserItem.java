package eum.item.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user_items")
public class UserItem {
    @EmbeddedId
    private UserItemId id;

    @Builder.Default
    @Column(name = "is_equipped", nullable = false, columnDefinition = "boolean default false")
    private Boolean isEquipped = false; // 장착 여부

    @CreationTimestamp
    @Column(name = "obtained_at", nullable = false)
    private LocalDateTime obtainedAt; // 아이템 획득 시간
}
