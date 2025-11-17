package eum.item.domain.dto.response;

import eum.item.domain.entity.UserItem;

import java.time.LocalDateTime;

public record UserItemResponse(
        String userId,
        Long itemId,
        Boolean isEquipped,
        LocalDateTime obtainedAt
) {
    public static UserItemResponse from(UserItem userItem) {
        return new UserItemResponse(
                userItem.getId().getUserId(),
                userItem.getId().getItemId(),
                userItem.getIsEquipped(),
                userItem.getObtainedAt()
        );
    }
}
