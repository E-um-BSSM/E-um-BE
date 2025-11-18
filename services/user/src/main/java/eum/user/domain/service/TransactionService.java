package eum.user.domain.service;

import eum.user.domain.dto.request.TransferRequest;
import eum.user.domain.entity.Transaction;
import eum.user.domain.entity.User;
import eum.user.domain.repository.TransactionRepository;
import eum.user.domain.repository.UserRepository;
import eum.user.global.exception.BadRequestException;
import eum.user.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void transfer(String senderId, TransferRequest request) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new NotFoundException("Sender not found"));
        User receiver = userRepository.findById(request.receiverId())
                .orElseThrow(() -> new NotFoundException("Receiver not found"));

        if (request.amount() == null || request.amount() <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        if (sender.getCurrency() < request.amount()) {
            throw new BadRequestException("Insufficient funds");
        }

        sender.subtractCurrency(request.amount());
        receiver.addCurrency(request.amount());

        transactionRepository.save(
                Transaction.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .amount(request.amount())
                        .type("transfer")
                        .build()
        );
    }

    @Transactional
    public void giveDaily(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getDailyCompensationAt() != null
                && user.getDailyCompensationAt().toLocalDate().isEqual(LocalDate.now())) {
            throw new BadRequestException("Daily compensation already claimed");
        }
        user.addCurrency(1L);
        user.updateDailyCompensationAt(java.time.LocalDateTime.now());

        transactionRepository.save(
                Transaction.builder()
                        .sender(null)
                        .receiver(user)
                        .amount(1L)
                        .type("daily")
                        .build()
        );
    }
}
