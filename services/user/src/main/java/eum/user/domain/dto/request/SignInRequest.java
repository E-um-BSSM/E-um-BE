package eum.user.domain.dto.request;

public record SignInRequest(
        String email,
        String password
) {
}
