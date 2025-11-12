package com.school.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.userservice.dto.AdminDTO;
import com.school.userservice.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
@Tag(name = "Admin", description = "Operations related to admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Operation(summary = "Sync admin data", description = "Receives admin info from auth-service and stores it in user-service")
	@PostMapping
	public ResponseEntity<Void> createAdmin(@RequestBody AdminDTO adminDTO) {
		System.out.println("Received admin sync: " + adminDTO.getUsername());
		adminService.save(adminDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "View admin dashboard", description = "Returns the logged-in admin's profile", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminDTO> getMyProfile(Authentication auth) {
		String username = auth.getName();
		AdminDTO dto = adminService.getAdminByUsername(username);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Update admin profile", description = "Allows a admin to update their own profile", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminDTO> updateMyProfile(@Valid @RequestBody AdminDTO dto, Authentication auth) {
		String username = auth.getName();
		AdminDTO updated = adminService.updateAdminProfile(username, dto);
		return ResponseEntity.ok(updated);
	}

}
