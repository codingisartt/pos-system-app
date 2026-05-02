package com.tnb.possystem.payload.response;

import com.tnb.possystem.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;
}
