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

import com.school.userservice.dto.StudentDTO;
import com.school.userservice.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@Tag(name = "Students", description = "Operations related to students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Operation(summary = "Sync student data", description = "Receives student info from auth-service and stores it in user-service")
	@PostMapping
	public ResponseEntity<Void> createStudent(@RequestBody StudentDTO studentDTO) {
		System.out.println("Received student sync: " + studentDTO.getUsername());
		studentService.save(studentDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "View student dashboard", description = "Returns the logged-in student's profile", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDTO> getMyProfile(Authentication auth) {
		String username = auth.getName();
		StudentDTO dto = studentService.getStudentByUsername(username);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Update student profile", description = "Allows a student to update their own profile", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDTO> updateMyProfile(@Valid @RequestBody StudentDTO dto, Authentication auth) {
		String username = auth.getName();
		StudentDTO updated = studentService.updateStudentProfile(username, dto);
		return ResponseEntity.ok(updated);
	}

}