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

    private String LastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String pwd;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public void transfer(BigDecimal value) {
        this.balance.subtract(value);
    }

    public void deposit(BigDecimal value) {
        this.balance.add(value);
    }


}
