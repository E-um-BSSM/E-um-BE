package eum.user.domain.repository;

import eum.user.domain.entity.UserBadge;
import eum.user.domain.entity.UserBadgeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, UserBadgeId> {
    List<UserBadge> findByIdUserId(String userId);
}
