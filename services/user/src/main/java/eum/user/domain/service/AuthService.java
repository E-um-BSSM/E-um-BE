package eum.user.domain.service;

import eum.user.domain.dto.reponse.RefreshResponse;
import eum.user.domain.dto.reponse.SignInResponse;
import eum.user.domain.dto.reponse.SignOutResponse;
import eum.user.domain.dto.reponse.SignUpResponse;
import eum.user.domain.dto.request.RefreshRequest;
import eum.user.domain.dto.request.SignInRequest;
import eum.user.domain.dto.request.SignOutRequest;
import eum.user.domain.dto.request.SignUpRequest;
import eum.user.domain.entity.User;
import eum.user.domain.repository.UserRepository;
import eum.user.global.constclass.SystemRole;
import eum.user.global.exception.BadRequestException;
import eum.user.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        userRepository.findByEmail(request.email())
                .ifPresent(u -> {throw new BadRequestException("Email already exists");});

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(SystemRole.STUDENT)
                .build();
        User saved = userRepository.save(user);
        return SignUpResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String access = UUID.randomUUID().toString();
        String refresh = UUID.randomUUID().toString();
        String expiresIn = LocalDateTime.now().plusHours(1)
                .format(DateTimeFormatter.ISO_DATE_TIME);

        return new SignInResponse(
                user.getUsername(),
                user.getEquippedBadge(),
                user.getRole(),
                access,
                refresh,
                expiresIn,
                user.getCurrency(),
                user.getDailyCompensationAt()
        );
    }

    public RefreshResponse refresh(RefreshRequest request) {
        // 실제 구현 시 refreshToken 저장소와 검증 필요
        String access = UUID.randomUUID().toString();
        String expiresIn = LocalDateTime.now().plusHours(1)
                .format(DateTimeFormatter.ISO_DATE_TIME);
        return new RefreshResponse(access, expiresIn);
    }

    public SignOutResponse signOut(SignOutRequest request) {
        // 실제 구현 시 refreshToken 폐기
        return new SignOutResponse("signed out");
    }
}
