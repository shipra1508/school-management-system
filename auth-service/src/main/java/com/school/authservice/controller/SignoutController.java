package com.school.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignoutController {

	@PostMapping("/signout")
	public ResponseEntity<String> signout() {
		return ResponseEntity.ok("Signed out successfully !!");
	}

}
