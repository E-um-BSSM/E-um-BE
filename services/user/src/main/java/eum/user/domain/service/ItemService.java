package eum.user.domain.service;

import eum.user.domain.dto.reponse.ItemSearchResponse;
import eum.user.domain.dto.reponse.RandomBoxResponse;
import eum.user.domain.dto.request.ItemEquipmentRequest;
import eum.user.domain.entity.Item;
import eum.user.domain.entity.User;
import eum.user.domain.entity.UserItem;
import eum.user.domain.entity.UserItemId;
import eum.user.domain.repository.ItemRepository;
import eum.user.domain.repository.UserItemRepository;
import eum.user.domain.repository.UserRepository;
import eum.user.global.exception.BadRequestException;
import eum.user.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ItemSearchResponse> listItems(String userId) {
        return userItemRepository.findByIdUserId(userId).stream()
                .map(ui -> new ItemSearchResponse(
                        ui.getItem().getId(),
                        ui.getIsEquipped(),
                        ui.getObtainedAt()
                )).collect(Collectors.toList());
    }

    @Transactional
    public void equipItem(String userId, Long itemId, ItemEquipmentRequest request) {
        UserItemId id = UserItemId.builder()
                .userId(userId)
                .itemId(itemId)
                .build();
        UserItem userItem = userItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not owned"));
        userItem.changeEquipped(Boolean.TRUE.equals(request.isEquipped()));
    }

    @Transactional
    public RandomBoxResponse openRandomBox(String userId, Long boxId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        // 임시 구현: 임의 아이템 지급
        Item item = itemRepository.findAll().stream().findAny()
                .orElseThrow(() -> new BadRequestException("No items available"));
        UserItemId id = UserItemId.builder()
                .userId(user.getUserId())
                .itemId(item.getId())
                .build();
        userItemRepository.findById(id).ifPresent(ui -> {
            throw new BadRequestException("Already owns this item");
        });
        UserItem userItem = UserItem.builder()
                .id(id)
                .user(user)
                .item(item)
                .isEquipped(false)
                .obtainedAt(LocalDateTime.now())
                .build();
        userItemRepository.save(userItem);
        return new RandomBoxResponse(boxId, item.getId());
    }
}
