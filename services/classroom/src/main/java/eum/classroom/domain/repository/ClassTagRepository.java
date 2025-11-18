package eum.classroom.domain.repository;

import eum.classroom.domain.entity.ClassTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTagRepository extends JpaRepository<ClassTag, Long> {
    java.util.List<ClassTag> findByClassId(Long classId);
}
