package eum.classroom.domain.repository;

import eum.classroom.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    java.util.List<Schedule> findByClassId(Long classId);
}
