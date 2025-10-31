package com.school.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Signout", description = "Handles user signout")
public class SignoutController {

	@Operation(summary = "Sign out user", description = "Signs out the user")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Signout successful"),
			@ApiResponse(responseCode = "401", description = "Unauthorized access (invalid or missing token)") })
	@PostMapping("/signout")
	public ResponseEntity<String> signout() {
		return ResponseEntity.ok("Signed out successfully !!");
	}

}
