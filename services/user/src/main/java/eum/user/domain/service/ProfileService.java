package eum.user.domain.service;

import eum.user.domain.dto.reponse.ProfileResponse;
import eum.user.domain.dto.request.ProfileRequest;
import eum.user.domain.entity.Profile;
import eum.user.domain.entity.User;
import eum.user.domain.repository.ProfileRepository;
import eum.user.domain.repository.UserRepository;
import eum.user.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Profile profile = profileRepository.findById(user.getUserId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        return toResponse(user.getUsername(), profile);
    }

    @Transactional
    public ProfileResponse updateProfile(String currentUserId, ProfileRequest request) {
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Profile profile = profileRepository.findById(currentUserId)
                .orElse(Profile.builder()
                        .user(user)
                        .color(request.color())
                        .border(request.border())
                        .background(request.background())
                        .build());

        profile.update(request.color(), request.border(), request.background());
        Profile saved = profileRepository.save(profile);
        return toResponse(user.getUsername(), saved);
    }

    @Transactional(readOnly = true)
    public List<ProfileResponse> listProfiles(String username) {
        // 명세에 맞춰 username 기반 조회. 여기서는 단일 사용자만 반환.
        ProfileResponse profile = getProfile(username);
        return List.of(profile);
    }

    private ProfileResponse toResponse(String username, Profile profile) {
        return new ProfileResponse(
                username,
                profile.getColor(),
                profile.getBorder(),
                profile.getBackground(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
