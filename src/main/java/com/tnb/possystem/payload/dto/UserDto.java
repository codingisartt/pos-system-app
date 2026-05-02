package com.tnb.possystem.payload.dto;

import com.tnb.possystem.domain.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;

    private String fullName;
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    private UserRole role;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
