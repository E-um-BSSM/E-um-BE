package eum.user.domain.service;

import eum.user.domain.dto.reponse.BadgeResponse;
import eum.user.domain.entity.Badge;
import eum.user.domain.entity.User;
import eum.user.domain.entity.UserBadge;
import eum.user.domain.entity.UserBadgeId;
import eum.user.domain.repository.BadgeRepository;
import eum.user.domain.repository.UserBadgeRepository;
import eum.user.domain.repository.UserRepository;
import eum.user.global.exception.BadRequestException;
import eum.user.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<BadgeResponse> listBadges(String userId) {
        return userBadgeRepository.findByIdUserId(userId).stream()
                .map(ub -> new BadgeResponse(
                        ub.getBadge().getName(),
                        ub.getBadge().getDescription(),
                        ub.getEarnedAt()
                )).collect(Collectors.toList());
    }

    @Transactional
    public void registerBadge(String userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new NotFoundException("Badge not found"));

        UserBadgeId id = UserBadgeId.builder()
                .userId(user.getUserId())
                .badgeId(badge.getId())
                .build();
        userBadgeRepository.findById(id).ifPresent(ub -> {
            throw new BadRequestException("Badge already earned");
        });
        UserBadge userBadge = UserBadge.builder()
                .id(id)
                .user(user)
                .badge(badge)
                .build();
        userBadgeRepository.save(userBadge);
    }

    @Transactional
    public void equipBadge(String userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        UserBadgeId id = UserBadgeId.builder()
                .userId(userId)
                .badgeId(badgeId)
                .build();
        userBadgeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Badge not owned"));
        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new NotFoundException("Badge not found"));
        user.changeEquippedBadge(badge.getName());
    }

    @Transactional
    public void releaseBadge(String userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.changeEquippedBadge(null);
    }
}
