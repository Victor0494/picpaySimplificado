package com.picpaysimplificado.entities;

import com.picpaysimplificado.constant.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String pwd;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void tranferir(BigDecimal valor) {
        this.balance = this.balance.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        this.balance = this.balance.add(valor);
    }
}
