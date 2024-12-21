package com.example.user_service.dtos.request;

import com.example.user_service.models.Role;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SignupRequestDto {
    private String name;
    private String email;
    private String mobile;
    private String password;
    private List<Role> roles;
}
