package com.example.user_service.controllers;

import com.example.user_service.dtos.TokenDto;
import com.example.user_service.dtos.UserDto;
import com.example.user_service.dtos.request.LoginRequestDto;
import com.example.user_service.dtos.request.LogoutRequestDto;
import com.example.user_service.dtos.request.SignupRequestDto;
import com.example.user_service.models.Token;
import com.example.user_service.models.User;
import com.example.user_service.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody final SignupRequestDto signupRequestDto){
        User savedUser = authService.signUp(signupRequestDto.getName(), signupRequestDto.getMobile(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return UserDto.from(savedUser);
    }

    @PostMapping("/login")
    public TokenDto logIn(@RequestBody final LoginRequestDto loginRequestDto){
        Token token = authService.logIn(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return TokenDto.from(token);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody final LogoutRequestDto logoutRequestDto){
        authService.logOut(logoutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token){
        User user = authService.validateToken(token);
        return UserDto.from(user);
    }
}
