package com.picpaysimplificado.service;

import com.picpaysimplificado.client.ExternalApiClient;
import com.picpaysimplificado.constant.ErrorCodes;
import com.picpaysimplificado.constant.UserType;
import com.picpaysimplificado.converter.UserCreateDTOToUserEntityConverter;
import com.picpaysimplificado.dto.UserCreateDTO;
import com.picpaysimplificado.entities.Usuario;
import com.picpaysimplificado.exception.InsufficientBalanceException;
import com.picpaysimplificado.exception.InvalidUserTypeException;
import com.picpaysimplificado.exception.UserNotAuthorizedException;
import com.picpaysimplificado.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

    private final ExternalApiClient client;

    private final UserCreateDTOToUserEntityConverter converter;

    public void validateUserInfo(Usuario userSender, BigDecimal transferPayloadValue) {
        validateUser(userSender, transferPayloadValue);
        validateUserAuthorization();
    }

    private void validateUserAuthorization() {
        ResponseEntity<Map> response = client.authorizationAPI(System.getenv("AUTHORIZED_URI"));
        if(response.hasBody()) {
            if (!response.getStatusCode().equals(HttpStatus.OK) || !response.getBody().get("message").equals("Autorizado")) {
                throw new UserNotAuthorizedException("User not authorized");
            }
        }
    }

    private void validateUser(Usuario userSender, BigDecimal transferPayload) {
        if (userSender.getUserType().equals(UserType.MERCHANT)) {
            throw new InvalidUserTypeException(ErrorCodes.INVALID_USER_TYPE.getMessage());
        }

        if (userSender.getBalance().compareTo(transferPayload) < 0) {
            throw new InsufficientBalanceException(ErrorCodes.INSUFFICIENT_BALANCE.getMessage());
        }
    }

    public void createUser(UserCreateDTO userCreateDTO) {
        userRepository.save(converter.toUserEntity(userCreateDTO));
    }

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }
}
