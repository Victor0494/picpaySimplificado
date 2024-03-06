package com.picpaysimplificado.integration;

import com.picpaysimplificado.client.ExternalApiClient;
import com.picpaysimplificado.constant.UserType;
import com.picpaysimplificado.converter.UserCreateDTOToUserEntityConverter;
import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.dto.UserCreateDTO;
import com.picpaysimplificado.repository.UserRepository;
import com.picpaysimplificado.service.TransactionServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCreateDTOToUserEntityConverter converter;

    @Autowired
    private JacksonTester<TransferPayloadDTO> jacksonTester;

    @Autowired
    private ExternalApiClient client;

    UserCreateDTO sender = getUser("victor", "martins", UserType.COMMOM);

    UserCreateDTO receiver = getUser("marcos", "bueno", UserType.MERCHANT);

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userRepository.save(converter.toUserEntity(sender));
        userRepository.save(converter.toUserEntity(receiver));
    }

    @Test
    @DisplayName("Create a transaction with success")
    void createTransactionWithSuccess() throws Exception {
        TransferPayloadDTO payloadDTO = new TransferPayloadDTO(new BigDecimal(100), 1L, 2L);


        var response = mvc.perform(
                post("/transfer")
                        .content(jacksonTester.write(payloadDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        JSONObject jsonObject = new JSONObject(response.getContentAsString());

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(new BigDecimal(jsonObject.get("value").toString()), payloadDTO.value());
        assertEquals(jsonObject.get("senderFullName"), sender.getFullName());
        assertEquals(jsonObject.get("receiverFullName"), receiver.getFullName());
    }

    private UserCreateDTO getUser(String firstName, String lastName, UserType userType) {
        return UserCreateDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .document(UUID.randomUUID().toString())
                .balance(new BigDecimal(100))
                .email(UUID.randomUUID().toString())
                .pwd("123456")
                .userType(userType).build();
    }
}
