package eum.classroom.domain.repository;

import eum.classroom.domain.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
