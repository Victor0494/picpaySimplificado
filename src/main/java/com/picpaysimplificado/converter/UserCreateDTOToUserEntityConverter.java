package com.picpaysimplificado.converter;

import com.picpaysimplificado.dto.UserCreateDTO;
import com.picpaysimplificado.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserCreateDTOToUserEntityConverter {

    public Usuario toUserEntity(UserCreateDTO dto) {
        return Usuario.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .document(dto.document())
                .email(dto.email())
                .pwd(dto.pwd())
                .balance(dto.balance())
                .userType(dto.userType()).build();
    }
}
