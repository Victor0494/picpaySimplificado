package com.picpaysimplificado.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transactions")
@Entity(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Usuario sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Usuario receiver;

    private LocalDateTime localDate;

    public Transaction(BigDecimal value, Usuario userSender, Usuario userReciever, LocalDateTime dateTime) {
        this.id = this.getId();
        this.amount = value;
        this.sender = userSender;
        this.receiver = userReciever;
        this.localDate = dateTime;
    }
}
