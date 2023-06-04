package com.vityoube.demoloyaltysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@ToString(exclude = {"id"})
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product(){

    }
}
