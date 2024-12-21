package com.example.user_service.repostories;

import com.example.user_service.models.Token;
import com.example.user_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<List<Token>> findByUser(User user);
    Optional<Token> findByValueAndIsDeletedAndExpiryGreaterThan(String token, Boolean isDeleted, LocalDateTime expriyDate);
}
