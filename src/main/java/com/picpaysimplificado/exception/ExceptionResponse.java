package com.picpaysimplificado.exception;

import com.picpaysimplificado.constant.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private String code;
    private String message;
    private List<String> details;

    public ExceptionResponse(String message) {
    }

    public ExceptionResponse(HttpStatus status, String message) {
        this.code = String.valueOf(status.value());
        this.message = message;
        this.details = Collections.singletonList(message);
    }
}
