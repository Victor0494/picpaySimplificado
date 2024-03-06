package com.picpaysimplificado.dto;

import com.picpaysimplificado.entities.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(Long transactionId, BigDecimal value, String senderFullName, String receiverFullName, LocalDateTime localDateTime) {

    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getAmount(), transaction.getSender().getFullName(), transaction.getReceiver().getFullName(), transaction.getLocalDate());
    }
}
