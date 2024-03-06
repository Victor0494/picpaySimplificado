package com.picpaysimplificado.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

	USER_NOT_FOUND("User not found."),

    USER_NOT_AUTHORIZED("User not authorized"),
    INVALID_USER_TYPE("Invalid user type"),
    INSUFFICIENT_BALANCE("Insufficient balance"),

    ERRO_SEND_EMAIL("Fail to send the e-mail notification to the user");

    private final String message;

}
