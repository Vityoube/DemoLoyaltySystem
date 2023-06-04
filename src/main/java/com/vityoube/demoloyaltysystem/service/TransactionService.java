package com.vityoube.demoloyaltysystem.service;

import com.vityoube.demoloyaltysystem.domain.Product;
import com.vityoube.demoloyaltysystem.domain.Transaction;
import com.vityoube.demoloyaltysystem.domain.TransactionStatus;
import com.vityoube.demoloyaltysystem.domain.UserAccount;
import com.vityoube.demoloyaltysystem.exception.ProductNotFoundException;
import com.vityoube.demoloyaltysystem.exception.UserNotAuthenticatedException;
import com.vityoube.demoloyaltysystem.repository.ProductRepository;
import com.vityoube.demoloyaltysystem.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(UserAccount user, List<String> items) throws ProductNotFoundException, UserNotAuthenticatedException {
        log.info("createTransaction : begin : user [{}], items [{}]", user, items);
        if (user == null){
            throw new UserNotAuthenticatedException();
        }
        double sum = 0.0;
        for(String item : items){
            Product product = productRepository.findByName(item);
            if (product == null){
                throw new ProductNotFoundException(item);
            }
            sum += product.getPrice();
        }
        log.debug("createTransaction : sum [{}]", sum);
        Transaction transaction = new Transaction(user, sum, Instant.now().toString(), TransactionStatus.STARTED);
        transactionRepository.save(transaction);
        log.info("createTransaction : finish : transaction [{}]", transaction);
        return transaction;
    }

    public long calculatePoints(Transaction transaction) {
        log.info("calculatePoints : begin : transaction [{}]", transaction);
        long points = 0l;
        long sum = (long)Math.floor(transaction.getSum());
        log.debug("calculatePoints : sum [{}]", sum);
        if (sum > 100){
            points +=  (( sum - 100 ) * 2 ) + 50 * 1;
        } else if ( sum > 50){
            points += (sum - 50) * 1;
        }
        log.info("calculatePoints : finish : points [{}]", points);
        return points;
    }

    public Transaction finishTransaction(Transaction transaction) {
        log.info("finishTransaction : begin : transaction [{}]", transaction);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
        log.info("finishTransaction : finish : transaction [{}]", transaction);
        return transaction;
    }
}
