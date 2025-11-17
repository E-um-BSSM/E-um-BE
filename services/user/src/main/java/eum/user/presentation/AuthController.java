package eum.user.presentation;

import eum.user.domain.dto.reponse.RefreshResponse;
import eum.user.domain.dto.reponse.SignInResponse;
import eum.user.domain.dto.reponse.SignOutResponse;
import eum.user.domain.dto.reponse.SignUpResponse;
import eum.user.domain.dto.request.RefreshRequest;
import eum.user.domain.dto.request.SignInRequest;
import eum.user.domain.dto.request.SignOutRequest;
import eum.user.domain.dto.request.SignUpRequest;
import eum.user.domain.service.AuthService;
import eum.user.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponse>> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(authService.signUp(request)));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(authService.signIn(request)));
    }

    @PostMapping("/signout")
    public ResponseEntity<ApiResponse<SignOutResponse>> signOut(@RequestBody SignOutRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(authService.signOut(request)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshResponse>> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(authService.refresh(request)));
    }
}
