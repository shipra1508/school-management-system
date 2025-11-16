package com.school.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.userservice.dto.TeacherDTO;
import com.school.userservice.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teachers", description = "Operations related to teachers")
public class TeacherController {

		@Autowired
		private TeacherService teacherService;

		@Operation(summary = "Sync teacher data", description = "Receives teacher info from auth-service and stores it in user-service")
		@PostMapping
		public ResponseEntity<Void> createTeacher(@RequestBody TeacherDTO teacherDTO) {
			System.out.println("Received teacher sync: " + teacherDTO.getUsername());
			teacherService.save(teacherDTO);
			return ResponseEntity.ok().build();
		}

		@Operation(summary = "View teacher dashboard", description = "Returns the logged-in teacher's profile", security = @SecurityRequirement(name = "bearerAuth"))
		@GetMapping("/dashboard")
		@PreAuthorize("hasRole('TEACHER')")
		public ResponseEntity<TeacherDTO> getMyProfile(Authentication auth) {
			String username = auth.getName();
			TeacherDTO dto = teacherService.getTeacherByUsername(username);
			return ResponseEntity.ok(dto);
		}

		@Operation(summary = "Update teacher profile", description = "Allows a teacher to update their own profile", security = @SecurityRequirement(name = "bearerAuth"))
		@PutMapping("/update")
		@PreAuthorize("hasRole('TEACHER')")
		public ResponseEntity<TeacherDTO> updateMyProfile(@Valid @RequestBody TeacherDTO dto, Authentication auth) {
			String username = auth.getName();
			TeacherDTO updated = teacherService.updateTeacherProfile(username, dto);
			return ResponseEntity.ok(updated);
		}
		
		@Operation(summary = "Admin can delete teacher records", description = "Allows admin to delete a teacher by username", security = @SecurityRequirement(name = "bearerAuth"))
		@DeleteMapping("/delete/{username}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Void> deleteTeacher(@PathVariable("username") String username) {
		    teacherService.deleteTeacherByUsername(username);
		    return ResponseEntity.ok().build();
		}

	}

