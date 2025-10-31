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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Handles user login and JWT token generation")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Operation(summary = "Login user", description = "Authenticates user credentials and returns a JWT token if valid")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
			@ApiResponse(responseCode = "401", description = "Invalid username or password"),
			@ApiResponse(responseCode = "400", description = "Validation error in request body"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
		String token = authService.authenticateAndGenerateToken(loginDTO);
		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}
}