package com.picpaysimplificado.controller;

import com.picpaysimplificado.dto.TransactionResponseDTO;
import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.service.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TransferControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TransferPayloadDTO> jacksonTester;

    @MockBean
    private TransactionServiceImpl transactionService;

    @Test
    void createTransaction() throws Exception {
        //Arrange
        TransferPayloadDTO payloadDTO = new TransferPayloadDTO(new BigDecimal(100), 1L, 2L);

        //Act
        var response = mvc.perform(
                post("/transfer")
                        .content(jacksonTester.write(payloadDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }


}