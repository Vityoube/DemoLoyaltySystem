package com.vityoube.demoloyaltysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    private Long id;
    @ManyToOne
    private User user_id;
    private Double sum;
    private Long points;
}
