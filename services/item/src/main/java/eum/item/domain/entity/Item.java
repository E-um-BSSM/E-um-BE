package eum.item.domain.entity;

import eum.item.global.constclass.ItemType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id; // 아이템 고유 아이디

    @Column(nullable = false)
    private String name; // 아이템 이름

    @Column(nullable = false)
    private ItemType type; // 아이템 타입 (color | border | background)

    @Column
    private String rarity; // 아이템 희귀도
}
