package com.picpaysimplificado.controller;

import com.picpaysimplificado.dto.TransferPayloadDTO;
import com.picpaysimplificado.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/transfer")
    public void transferOperation(TransferPayloadDTO transferPayload) {
        log.info("UserController.transferOperation - Start");

        userService.transfer(transferPayload);

        log.debug("UserController.transferOperation - End");
    }

}
