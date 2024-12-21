package com.example.user_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Data
@EqualsAndHashCode
public class Token extends BaseModel {
    private String value;
    private LocalDateTime expiry;
    @ManyToOne
    private User user;
}
