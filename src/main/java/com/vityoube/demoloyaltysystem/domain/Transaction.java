package com.vityoube.demoloyaltysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@ToString(exclude = {"id"})
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private UserAccount user;
    private Double sum;
    private Long points;
    @NonNull
    private String date;
    @NonNull
    private TransactionStatus status;
    public Transaction(UserAccount user, Double sum, String date, TransactionStatus status) {
        this.user = user;
        this.sum = sum;
        this.date = date;
        this.status = status;
        this.points = 0L;
    }

    public Transaction(UserAccount user, Double sum, String date, TransactionStatus status, Long points) {
        this.user = user;
        this.sum = sum;
        this.date = date;
        this.status = status;
        this.points = points;
    }

    public Transaction() {
    }
}