package com.example.user_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseModel {
    private String name;
    private String email;
    private String mobile;
    private String password;
    @ManyToMany
    List<Role> roles;
}
