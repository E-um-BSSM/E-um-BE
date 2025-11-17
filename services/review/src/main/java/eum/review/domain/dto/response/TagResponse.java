package eum.review.domain.dto.response;

import eum.review.domain.entity.Tag;

public record TagResponse(
        Long tagId,
        String name
) {
    public static TagResponse from(Tag tag) {
        return new TagResponse(
                tag.getTagId(),
                tag.getName()
        );
    }
}
