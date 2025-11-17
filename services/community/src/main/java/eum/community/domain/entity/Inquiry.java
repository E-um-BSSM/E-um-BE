package eum.community.domain.entity;

import eum.community.global.constclass.InquiryStatus;
import eum.community.global.constclass.InquiryType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@Table(name = "inquiries")
@DiscriminatorValue("INQUIRY")
public class Inquiry extends BaseThread {

    @Column(name = "inquiry_type", nullable = false)
    private InquiryType inquiryType; // 문의 유형

    @Builder.Default
    @Column(nullable = false, columnDefinition = "varchar default 'PENDING'")
    private InquiryStatus status = InquiryStatus.PENDING; // 문의 상태

    @Column(name = "admin_response", columnDefinition = "text")
    private String adminResponse; // 관리자 응답

    @Column(name = "responded_at")
    private LocalDateTime respondedAt; // 응답 시간

    @Column(name = "responded_by")
    private String respondedBy; // 응답한 관리자 user_id
}
