package com.vityoube.demoloyaltysystem.repository;

import com.vityoube.demoloyaltysystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository  extends JpaRepository<Long, User> {
    User findByUsername(String username);
}
