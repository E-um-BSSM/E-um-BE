package eum.classroom.domain.repository;

import eum.classroom.domain.entity.WaitingList;
import eum.classroom.domain.entity.WaitingListId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, WaitingListId> {
    java.util.List<WaitingList> findByIdClassId(Long classId);
}
