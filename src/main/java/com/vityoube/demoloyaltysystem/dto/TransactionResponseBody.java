package com.vityoube.demoloyaltysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseBody implements Serializable {
    private String message;
    private long transactionNumber;
    private long points;
    private String transactionDate;
    private double sum;
}
