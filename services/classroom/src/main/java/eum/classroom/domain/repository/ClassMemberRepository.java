package eum.classroom.domain.repository;

import eum.classroom.domain.entity.ClassMember;
import eum.classroom.domain.entity.ClassMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassMemberRepository extends JpaRepository<ClassMember, ClassMemberId> {
    java.util.List<ClassMember> findByIdClassId(Long classId);
}
