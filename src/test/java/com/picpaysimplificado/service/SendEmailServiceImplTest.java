package com.picpaysimplificado.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SendEmailServiceImplTest {

    @InjectMocks
    private SendEmailServiceImpl sendEmailService;

    @Mock
    private RestTemplate restTemplate;



    @Test
    @DisplayName("Validate the email sending with success")
    void sendEmailWithSuccess() {
        //Arrange
        when(restTemplate.postForEntity(any(),any(), any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        //ACT
        sendEmailService.sendEmail("teste@test.com", "Test");

        //Asserts
    }
}