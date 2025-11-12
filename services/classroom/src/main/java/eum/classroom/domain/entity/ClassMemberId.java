package eum.classroom.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassMemberId implements Serializable {
    private Long classId; // 참조 클래스 고유 아이디
    private String userId; // 참조 유저 고유 아이디
}
