package com.vityoube.demoloyaltysystem.repository;

import com.vityoube.demoloyaltysystem.domain.Transaction;
import com.vityoube.demoloyaltysystem.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(UserAccount user);
    List<Transaction> findAllBySum(Double sum);
    List<Transaction> findAllByPoints(Long points);
}
