package com.tnb.possystem.service;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.payload.dto.UserDto;
import com.tnb.possystem.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto user) throws UserException;
    AuthResponse login(UserDto user) throws UserException;
}
