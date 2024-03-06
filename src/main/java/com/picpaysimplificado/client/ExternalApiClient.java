package com.picpaysimplificado.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ExternalApiClient {

    private final RestTemplate restTemplate;

    public ResponseEntity<Map> authorizationAPI(String uri) {
       return restTemplate.getForEntity(uri, Map.class);
    }
}
