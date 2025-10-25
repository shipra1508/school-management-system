package com.school.authservice.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.authservice.dto.LoginDTO;
import com.school.authservice.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid @RequestBody LoginDTO loginDTO) {
        String token = authService.authenticateAndGenerateToken(loginDTO);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}