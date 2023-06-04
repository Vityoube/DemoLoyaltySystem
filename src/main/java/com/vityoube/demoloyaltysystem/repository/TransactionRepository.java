package com.vityoube.demoloyaltysystem.repository;

import com.vityoube.demoloyaltysystem.domain.Transaction;
import com.vityoube.demoloyaltysystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Long, Transaction> {
    List<Transaction> findAllByUser(User user);
    List<Transaction> findAllBySum(Double sum);
    List<Transaction> findAllByPoints(Long points);
}
