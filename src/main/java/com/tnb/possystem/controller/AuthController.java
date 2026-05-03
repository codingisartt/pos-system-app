package com.tnb.possystem.controller;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.payload.dto.UserDto;
import com.tnb.possystem.payload.response.AuthResponse;
import com.tnb.possystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody UserDto userDto
    ) throws UserException {
        return ResponseEntity.ok(authService.signup(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDto userDto
    ) throws UserException {
        return ResponseEntity.ok(authService.login(userDto));
    }

}
