package eum.classroom.domain.repository;

import eum.classroom.domain.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    java.util.List<Assignment> findByClassId(Long classId);
}
