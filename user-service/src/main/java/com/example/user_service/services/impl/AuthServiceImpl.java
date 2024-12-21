package com.example.user_service.services.impl;

import com.example.user_service.exceptions.TokenCountExceededException;
import com.example.user_service.exceptions.ValidTokenNotFoundException;
import com.example.user_service.models.Token;
import com.example.user_service.models.User;
import com.example.user_service.repostories.TokenRepository;
import com.example.user_service.repostories.UserRepository;
import com.example.user_service.services.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public User signUp(String name, String mobile, String email, String password) {
        Optional<User> userDetails = userRepository.findByEmail(email);
        if(userDetails.isPresent()){
            return userDetails.get();
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setMobile(mobile);
        return userRepository.save(user);
    }

    @Override
    public Token logIn(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty()){
            return null;
        }
        User user = existingUser.get();
        Optional<List<Token>> tokens = tokenRepository.findByUser(user);
        if(tokens.get().size()>=2){
            throw new TokenCountExceededException("Device Limit Reached");
        }

        Token token = null;
        if(passwordEncoder.matches(password, user.getPassword())){
            token = new Token();
            token.setUser(user);
            token.setExpiry(LocalDateTime.now().plusDays(30));
            //token.setExpiry(LocalDateTime.now());
            token.setValue(RandomStringUtils.randomAlphanumeric(120));
        }
        return tokenRepository.save(token);
    }

    @Override
    public void logOut(String tokenValue) {
        Optional<Token> validToken = tokenRepository.findByValueAndIsDeletedAndExpiryGreaterThan(tokenValue, false, LocalDateTime.now());
        if(validToken.isEmpty()) throw new ValidTokenNotFoundException("Valid Token Not Found");
        Token token = validToken.get();
        token.setIsDeleted(true);
        tokenRepository.save(token);
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> validToken = tokenRepository.findByValueAndIsDeletedAndExpiryGreaterThan(token, false, LocalDateTime.now());
        if(validToken.isEmpty()) throw new ValidTokenNotFoundException("Valid Token Not Found");
        return validToken.get().getUser();
    }
}
