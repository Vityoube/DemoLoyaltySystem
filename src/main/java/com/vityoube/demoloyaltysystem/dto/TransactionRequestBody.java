package com.vityoube.demoloyaltysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestBody implements Serializable {
    private List<String> items;
}
