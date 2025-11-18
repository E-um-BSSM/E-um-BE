package eum.user.presentation;

import eum.user.domain.dto.reponse.BadgeResponse;
import eum.user.domain.dto.reponse.ItemSearchResponse;
import eum.user.domain.dto.reponse.ProfileResponse;
import eum.user.domain.dto.reponse.RandomBoxResponse;
import eum.user.domain.dto.request.ItemEquipmentRequest;
import eum.user.domain.dto.request.ProfileRequest;
import eum.user.domain.dto.request.TransferRequest;
import eum.user.domain.service.BadgeService;
import eum.user.domain.service.ItemService;
import eum.user.domain.service.ProfileService;
import eum.user.domain.service.TransactionService;
import eum.user.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final BadgeService badgeService;
    private final ProfileService profileService;
    private final ItemService itemService;
    private final TransactionService transactionService;

    @GetMapping("/users/badges")
    public ResponseEntity<ApiResponse<List<BadgeResponse>>> badges(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(badgeService.listBadges(auth.getName())));
    }

    @PostMapping("/users/badges/{badgeId}")
    public ResponseEntity<ApiResponse<Void>> registerBadge(Authentication auth,
                                                           @PathVariable("badgeId") Long badgeId) {
        badgeService.registerBadge(auth.getName(), badgeId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/users/badges/{badgeId}/equip")
    public ResponseEntity<ApiResponse<Void>> equipBadge(Authentication auth,
                                                        @PathVariable("badgeId") Long badgeId) {
        badgeService.equipBadge(auth.getName(), badgeId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/users/badges/{badgeId}/release")
    public ResponseEntity<ApiResponse<Void>> releaseBadge(Authentication auth,
                                                          @PathVariable("badgeId") Long badgeId) {
        badgeService.releaseBadge(auth.getName(), badgeId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/profiles/{username}")
    public ResponseEntity<ApiResponse<ProfileResponse>> profile(@PathVariable("username") String username) {
        return ResponseEntity.ok(ApiResponse.ok(profileService.getProfile(username)));
    }

    @PatchMapping("/profiles")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(Authentication auth,
                                                                      @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(profileService.updateProfile(auth.getName(), request)));
    }

    @GetMapping("/profiles/{username}/list")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> profileList(@PathVariable("username") String username) {
        return ResponseEntity.ok(ApiResponse.ok(profileService.listProfiles(username)));
    }

    @GetMapping("/items")
    public ResponseEntity<ApiResponse<List<ItemSearchResponse>>> items(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(itemService.listItems(auth.getName())));
    }

    @PostMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> equipItem(Authentication auth,
                                                      @PathVariable("itemId") Long itemId,
                                                      @RequestBody ItemEquipmentRequest request) {
        itemService.equipItem(auth.getName(), itemId, request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/items/randomboxs/{boxId}")
    public ResponseEntity<ApiResponse<RandomBoxResponse>> openRandomBox(Authentication auth,
                                                                        @PathVariable("boxId") Long boxId) {
        return ResponseEntity.ok(ApiResponse.ok(itemService.openRandomBox(auth.getName(), boxId)));
    }

    @PostMapping("/transactions/transfer")
    public ResponseEntity<ApiResponse<Void>> transfer(Authentication auth,
                                                      @RequestBody TransferRequest request) {
        transactionService.transfer(auth.getName(), request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/transaction/daily")
    public ResponseEntity<ApiResponse<Void>> daily(Authentication auth) {
        transactionService.giveDaily(auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
