package com.vityoube.demoloyaltysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
}
