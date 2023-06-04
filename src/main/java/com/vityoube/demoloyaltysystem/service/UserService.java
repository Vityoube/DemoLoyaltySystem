package com.vityoube.demoloyaltysystem.service;

import com.vityoube.demoloyaltysystem.domain.UserAccount;
import com.vityoube.demoloyaltysystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserAccount findUserByUsername(String username){
        log.info("findUserByUsername : begin : username [{}]", username);
        UserAccount user = userRepository.findByUsername(username);
        log.info("findUserByUsername : finish : user [{}]", user);
        return user;
    }
}
