package com.picpaysimplificado.service;

import com.picpaysimplificado.client.ExternalApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SendEmailServiceImpl {

    private final static String MESSAGE_URI = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    private final ExternalApiClient client;

    public SendEmailServiceImpl(ExternalApiClient client) {
        this.client = client;
    }

    public void sendEmail() {

        try {
            client.authorizationAPI(MESSAGE_URI);
        } catch (IOException | InterruptedException e) {
            log.error("SendEmailServiceImpl.sendEmail - Error sending the e-mail ");
        }
    }
}
