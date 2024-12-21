package com.example.user_service.services;

import com.example.user_service.models.Token;
import com.example.user_service.models.User;

public interface AuthService {
    User signUp(String name, String mobile, String email, String password);
    Token logIn(String email, String password);
    void logOut(String token);
    User validateToken(String token);
}
