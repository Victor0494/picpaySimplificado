package com.picpaysimplificado.service;

import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.entities.Usuario;
import com.picpaysimplificado.exception.UserNotFoundException;
import com.picpaysimplificado.repository.TransactionRepository;
import com.picpaysimplificado.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    private JacksonTester<TransferPayloadDTO> jacksonTester;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private SendEmailServiceImpl sendEmailService;

    @Captor
    private ArgumentCaptor<Transaction> argumentCaptor;


    @Test
    @DisplayName("Create transaction with success")
    void createTransactionWithSuccess() {
        Usuario userReceiver = getUser("Carlos", "Marcondes");
        Usuario userSender = getUser("victor", "martins");

        TransferPayloadDTO payloadDTO = new TransferPayloadDTO(new BigDecimal(50), 1L, 2L);
        when(userRepository.findById(2L)).thenReturn(Optional.of(userReceiver));
        when(userRepository.findById(1L)).thenReturn(Optional.of(userSender));


        var response = transactionService.createTransaction(payloadDTO);

        assertEquals(response.receiverFullName(), userReceiver.getFullName());
        assertEquals(response.senderFullName(), userSender.getFullName());
        assertEquals(response.value(), payloadDTO.value());
    }

    @Test
    @DisplayName("Validate the process of saving the information on database")
    void validarSavingProcess() {
        Usuario userReceiver = getUser("Carlos", "Marcondes");
        Usuario userSender = getUser("victor", "martins");

        TransferPayloadDTO payloadDTO = new TransferPayloadDTO(new BigDecimal(50), 1L, 2L);
        when(userRepository.findById(2L)).thenReturn(Optional.of(userReceiver));
        when(userRepository.findById(1L)).thenReturn(Optional.of(userSender));


        var response = transactionService.createTransaction(payloadDTO);
        verify(transactionRepository).save(argumentCaptor.capture());
        Transaction transaction = argumentCaptor.getValue();
        assertEquals(response.value(), transaction.getAmount());
        assertEquals(response.senderFullName(), transaction.getSender().getFullName());
        assertEquals(response.receiverFullName(), transaction.getReceiver().getFullName());

    }

    @Test
    @DisplayName("Create transaction with no user found")
    void createTransactionUserNotFound() {
        TransferPayloadDTO payloadDTO = new TransferPayloadDTO(new BigDecimal(50), 1L, 2L);

        assertThrows(UserNotFoundException.class, () -> transactionService.createTransaction(payloadDTO));
    }

    private Usuario getUser(String firstName, String lastName) {
        return Usuario.builder()
                .firstName(firstName)
                .lastName(lastName)
                .balance(new BigDecimal(100))
                .email("teste@test.com.br").build();
    }
}