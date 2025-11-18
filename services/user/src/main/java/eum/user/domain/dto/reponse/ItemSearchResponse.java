package eum.user.domain.dto.reponse;

import java.time.LocalDateTime;

public record ItemSearchResponse(
        Long itemId,
        Boolean isEquipped,
        LocalDateTime obtainedAt
) {
}
