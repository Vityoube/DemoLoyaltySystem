package com.vityoube.demoloyaltysystem.controller;

import com.vityoube.demoloyaltysystem.domain.Transaction;
import com.vityoube.demoloyaltysystem.domain.TransactionStatus;
import com.vityoube.demoloyaltysystem.domain.UserAccount;
import com.vityoube.demoloyaltysystem.dto.TransactionRequestBody;
import com.vityoube.demoloyaltysystem.dto.TransactionResponseBody;
import com.vityoube.demoloyaltysystem.exception.ProductNotFoundException;
import com.vityoube.demoloyaltysystem.exception.UserNotAuthenticatedException;
import com.vityoube.demoloyaltysystem.service.TransactionService;
import com.vityoube.demoloyaltysystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<TransactionResponseBody>
    createTransaction(@RequestBody TransactionRequestBody requestBody){
        log.info("createTransaction : begin : received parameters [{}]", requestBody);
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserAccount user = userService.findUserByUsername(authentication.getName());
            log.debug("createTransaction : user [{}]. Creating transaction ...", user);
            Transaction transaction = transactionService.createTransaction(user, requestBody.getItems());
            log.debug("createTransaction : calculating points ...");
            long points = transactionService.calculatePoints(transaction);
            transaction.setPoints(points);
            log.debug("createTransaction : finishing transaction ...");
            transaction = transactionService.finishTransaction(transaction);
            log.debug("createTransaction : transaction [{}] is finished", transaction);
            TransactionResponseBody successBody = new TransactionResponseBody("Success",
                    transaction.getId(), transaction.getPoints(), transaction.getDate(),
                    transaction.getSum());
            log.info("createTransaction : finish : successBody [{}]", successBody);
            return new ResponseEntity<>(successBody, OK);
        } catch (ProductNotFoundException e) {
            Transaction transaction = new Transaction();
            transaction.setStatus(TransactionStatus.FAIL);
            transaction.setDate(Instant.now().toString());
            TransactionResponseBody errorBody = new TransactionResponseBody();
            errorBody.setMessage(format("Error occurred: %s", e.getMessage()));
            log.error("createTransaction : finish : error [{}]", e.getMessage(), e);
            return new ResponseEntity<>(errorBody, INTERNAL_SERVER_ERROR );
        } catch (UserNotAuthenticatedException e){
            TransactionResponseBody errorBody = new TransactionResponseBody();
            errorBody.setMessage(format("Error occurred: %s", e.getMessage()));
            log.error("createTransaction : finish : error [{}]", e.getMessage(), e);
            return new ResponseEntity<>(errorBody, UNAUTHORIZED );
        }
    }

}
