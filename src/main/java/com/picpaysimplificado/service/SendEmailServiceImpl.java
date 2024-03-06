package com.picpaysimplificado.service;

import com.picpaysimplificado.constant.ErrorCodes;
import com.picpaysimplificado.dto.NotificationRequestDTO;
import com.picpaysimplificado.exception.EmailNotificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.OK;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailServiceImpl {

    private final RestTemplate restTemplate;


    public void sendEmail(String email, String message) {
        NotificationRequestDTO notificationRequest = new NotificationRequestDTO(email, message);
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(System.getenv("MESSAGE_URI"), notificationRequest, String.class);
        } catch (EmailNotificationException exception) {
            throw new EmailNotificationException(ErrorCodes.ERRO_SEND_EMAIL.getMessage());

        }
        if(!OK.equals(response.getStatusCode())) {
            System.out.println("Erro ao enviar mensagem de notificação");
        }
    }
}
