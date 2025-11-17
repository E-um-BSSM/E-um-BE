package eum.classroom.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ClassMemberId implements Serializable {

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "user_id")
    private String userId;
}
