package eum.classroom.domain.repository;

import eum.classroom.domain.entity.ScheduleUser;
import eum.classroom.domain.entity.ScheduleUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, ScheduleUserId> {
}
