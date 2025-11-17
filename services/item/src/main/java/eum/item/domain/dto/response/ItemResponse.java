package eum.item.domain.dto.response;

import eum.item.domain.entity.Item;
import eum.item.global.constclass.ItemType;

public record ItemResponse(
        Long id,
        String name,
        ItemType type,
        String rarity
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getRarity()
        );
    }
}
