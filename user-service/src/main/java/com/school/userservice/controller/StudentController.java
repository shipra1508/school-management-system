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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

    //Student data sync from auth-service
	@PostMapping
	public ResponseEntity<Void> createStudent(@RequestBody StudentDTO studentDTO) {
	    System.out.println("Received student sync: " + studentDTO.getUsername());
	    studentService.save(studentDTO);
	    return ResponseEntity.ok().build();
	}

	
	// View own profile
	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDTO> getMyProfile(Authentication auth) {
		String username = auth.getName();
		StudentDTO dto = studentService.getStudentByUsername(username);
		return ResponseEntity.ok(dto);
	}

	// Update own profile
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDTO> updateMyProfile(@Valid @RequestBody StudentDTO dto, Authentication auth) {
		String username = auth.getName();
		StudentDTO updated = studentService.updateStudentProfile(username, dto);
		return ResponseEntity.ok(updated);
	}

}