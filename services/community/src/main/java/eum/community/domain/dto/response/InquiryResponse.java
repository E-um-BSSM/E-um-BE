package eum.community.domain.dto.response;

import eum.community.domain.entity.Inquiry;
import eum.community.global.constclass.InquiryStatus;
import eum.community.global.constclass.InquiryType;

import java.time.LocalDateTime;

public record InquiryResponse(
        Long id,
        String title,
        String content,
        InquiryType inquiryType,
        InquiryStatus status,
        String adminResponse,
        LocalDateTime respondedAt,
        String respondedBy,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static InquiryResponse from(Inquiry inquiry) {
        return new InquiryResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getInquiryType(),
                inquiry.getStatus(),
                inquiry.getAdminResponse(),
                inquiry.getRespondedAt(),
                inquiry.getRespondedBy(),
                inquiry.getCreatedBy(),
                inquiry.getCreatedAt(),
                inquiry.getUpdatedAt()
        );
    }
}
