package com.picpaysimplificado.dto;

import java.math.BigDecimal;

public record TransferPayloadDTO(BigDecimal value, String senderDocument, String receiverDocument) {
}
