package com.picpaysimplificado.service;

import com.picpaysimplificado.client.ExternalApiClient;
import com.picpaysimplificado.constant.UserType;
import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.entities.User;
import com.picpaysimplificado.exception.InvalidValidationException;
import com.picpaysimplificado.repository.TransactionRepository;
import com.picpaysimplificado.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final static String EXTERNAL_API_URI = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    private final SendEmailServiceImpl sendEmailService;

    private final ExternalApiClient client;

    public UserServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository, SendEmailServiceImpl sendEmailService, ExternalApiClient client) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.sendEmailService = sendEmailService;
        this.client = client;
    }


    public void transfer(TransferPayloadDTO transferPayload) {
        Optional<User> reciever = userRepository.findByDocument(transferPayload.receiverDocument());
        Optional<User> sender = userRepository.findByDocument(transferPayload.senderDocument());

        if (reciever.isEmpty() || sender.isEmpty()) {
            throw new InvalidValidationException("User not found");
        }
        User userSender = sender.get();
        User userReciever = reciever.get();

        validateUser(sender.get(), transferPayload.value());
        validateExternalAPI();

        userSender.transfer(transferPayload.value());
        userReciever.deposit(transferPayload.value());

        sendEmailService.sendEmail();

        transactionRepository.save(new Transaction(1L, transferPayload.value(), userSender, userReciever, LocalDate.now()));

    }

    private void validateExternalAPI() {
        try {
            var response = client.authorizationAPI(EXTERNAL_API_URI);
            if (!response.body().contains("Authorized")) {
                throw new InvalidValidationException("User not authorized");
            }

        } catch (IOException | InterruptedException e) {
            throw new InvalidValidationException(e.getMessage());
        }
    }

    private void validateUser(User userSender, BigDecimal transferPayload) {
        if (userSender.getUserType().equals(UserType.MERCHANT)) {
            throw new InvalidValidationException("Invalid UserType");
        }

        if (userSender.getBalance().compareTo(transferPayload) == -1) {
            throw new InvalidValidationException("The balance is insufficient");
        }
    }
}
