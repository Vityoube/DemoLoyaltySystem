package com.vityoube.demoloyaltysystem.security;

import com.vityoube.demoloyaltysystem.domain.UserAccount;
import com.vityoube.demoloyaltysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserAccount user = userRepository.findByUsername(username);
        String userPassword = new String(Base64.getDecoder().decode(user.getPassword()));
        if(userPassword.equals(password)){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
