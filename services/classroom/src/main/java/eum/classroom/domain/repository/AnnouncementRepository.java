package eum.classroom.domain.repository;

import eum.classroom.domain.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByClassRoomId(Long classId);
}
