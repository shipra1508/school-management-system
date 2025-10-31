package com.school.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.authservice.dto.SignUpDTO;
import com.school.authservice.service.SignupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Signup", description = "Registers new users and synchronizes student data with user-service")
public class SignupController {

	private final SignupService signupService;

	public SignupController(SignupService signupService) {
		this.signupService = signupService;
	}

	@Operation(summary = "Register new user", description = "Registers a new user with role-based access. If role is STUDENT, syncs data to user-service.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User registered successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input or user already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody SignUpDTO request, HttpServletRequest httpRequest) {
		try {
			String authHeader = httpRequest.getHeader("Authorization");
			String token = null;
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7); // remove "Bearer " prefix
			}

			signupService.signup(request, token);
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
