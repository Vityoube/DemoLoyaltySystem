package com.vityoube.demoloyaltysystem.init;

import com.vityoube.demoloyaltysystem.domain.Product;
import com.vityoube.demoloyaltysystem.domain.UserAccount;
import com.vityoube.demoloyaltysystem.repository.ProductRepository;
import com.vityoube.demoloyaltysystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class BootstrapApplicationListener implements ApplicationListener {
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public BootstrapApplicationListener(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("onApplicationEvent : begin");
        if (event instanceof ApplicationStartedEvent){
            log.info("onApplicationEvent : application started. Bootstrapping ...");
            String initialUsername = "john.doe";
            if (userRepository.findByUsername(initialUsername) == null){
                log.info("onApplicationEvent : User {} does not exist. Saving it in database ...", initialUsername);
                UserAccount user = new UserAccount(initialUsername, Base64.getEncoder().encodeToString("admin".getBytes()) );
                userRepository.save(user);
                log.info("onApplicationEvent : User {} is saved", initialUsername);
            }
            List<Product> products = Arrays.asList(new Product("Internet", 50.0),
                    new Product("TV", 30.0), new Product("Mobile Package", 70.0));
            products.stream().filter(product -> productRepository.findByName(product.getName())== null)
                    .forEach( product -> {
                        log.info("onApplicationEvent : Product {} does not exist. Saving it in database ...",
                                product.getName());
                        productRepository.save(product);
                        log.info("onApplicationEvent : Product {} is saved", product.getName());
                    });
            log.info("onApplicationEvent : Bootstrapping is finished");
        }
        log.info("onApplicationEvent : end");
    }
}
