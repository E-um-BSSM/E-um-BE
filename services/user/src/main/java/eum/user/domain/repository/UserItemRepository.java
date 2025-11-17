package eum.user.domain.repository;

import eum.user.domain.entity.UserItem;
import eum.user.domain.entity.UserItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, UserItemId> {
    List<UserItem> findByIdUserId(String userId);
}
