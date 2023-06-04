package com.vityoube.demoloyaltysystem.repository;

import com.vityoube.demoloyaltysystem.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
