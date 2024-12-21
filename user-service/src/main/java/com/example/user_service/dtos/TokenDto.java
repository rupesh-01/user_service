package com.example.user_service.dtos;

import com.example.user_service.models.Token;
import com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TokenDto {
    private String email;
    private String token;
    private LocalDateTime expirtyAt;

    public static TokenDto from(Token token){
        TokenDto tokenDto = new TokenDto();
        tokenDto.setEmail(token.getUser().getEmail());
        tokenDto.setToken(token.getValue());
        tokenDto.setExpirtyAt(token.getExpiry());
        return tokenDto;
    }
}
