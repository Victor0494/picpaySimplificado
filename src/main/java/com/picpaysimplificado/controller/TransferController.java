package com.picpaysimplificado.controller;

import com.picpaysimplificado.dto.TransactionResponseDTO;
import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransactionServiceImpl transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransferPayloadDTO transferPayload) {
        TransactionResponseDTO transaction = transactionService.createTransaction(transferPayload);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
