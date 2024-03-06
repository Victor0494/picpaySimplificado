package com.picpaysimplificado.service;

import com.picpaysimplificado.constant.ErrorCodes;
import com.picpaysimplificado.dto.TransactionResponseDTO;
import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.entities.Usuario;
import com.picpaysimplificado.exception.UserNotFoundException;
import com.picpaysimplificado.repository.TransactionRepository;
import com.picpaysimplificado.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl {

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    private final UserServiceImpl userService;

    private final SendEmailServiceImpl sendEmailService;

    public TransactionResponseDTO createTransaction(TransferPayloadDTO transferPayload) {
        log.info("TransactionServiceImpl.createTransaction - Start: value:{}", transferPayload.value());
        Optional<Usuario> reciever = userRepository.findById(transferPayload.receiverId());
        Optional<Usuario> sender = userRepository.findById(transferPayload.senderId());

        if (reciever.isEmpty() || sender.isEmpty()) {
           throw new UserNotFoundException(ErrorCodes.USER_NOT_FOUND.getMessage());
        }

        Usuario userSender = sender.get();
        Usuario userReciever = reciever.get();

        userService.validateUserInfo(userSender, transferPayload.value());

        userSender.tranferir(transferPayload.value());
        userReciever.depositar(transferPayload.value());

        Transaction transaction = new Transaction(transferPayload.value(), userSender, userReciever, LocalDateTime.now());
        saveTransaction(transaction);

        sendEmailToUsers(userReciever.getEmail(), userSender.getEmail(), transferPayload.value());

        log.info("TransactionServiceImpl.createTransaction - End: valueTranfered: {}", transferPayload.value());
        return new TransactionResponseDTO(transaction);
    }

    private void saveTransaction(Transaction transaction) {
        log.info("TransactionServiceImpl.saveTransaction");
        transactionRepository.save(transaction);
    }

    private void sendEmailToUsers(String receiverEmail, String senderEmail, BigDecimal value){
        sendEmailService.sendEmail(receiverEmail, "Transferência recebida no valor de R$" + value);
        sendEmailService.sendEmail(senderEmail, "Transferência enviado no valor de R$" + value);
    }
}
