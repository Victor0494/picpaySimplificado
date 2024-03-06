package com.picpaysimplificado.dto;

import com.picpaysimplificado.constant.UserType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserCreateDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String pwd, UserType userType) {

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
