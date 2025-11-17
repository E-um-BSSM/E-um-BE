package eum.user.domain.dto.reponse;

public record RefreshResponse(
        String accessToken,
        String expiresIn
) {

}
