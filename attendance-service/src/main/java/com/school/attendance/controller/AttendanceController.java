package com.school.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.attendance.dto.AttendanceDTO;
import com.school.attendance.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/mark/{courseCode}/{teacherId}")
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	public ResponseEntity<String> markAttendance(@PathVariable("courseCode") String courseCode,
			@PathVariable("teacherId") Long teacherId, @Valid @RequestBody AttendanceDTO request) {
		String message = attendanceService.markAttendance(courseCode, teacherId, request);
		return ResponseEntity.ok(message);
	}

	// Get attendance records for a student by username.
	@GetMapping("/student/{username}")
	public ResponseEntity<?> getAttendanceByStudent(@PathVariable("username") String username) {
		return ResponseEntity.ok(attendanceService.getAttendanceByStudent(username));
	}

	// Get attendance records for a course by course code.
	@GetMapping("/subject/{code}")
	public ResponseEntity<?> getAttendanceBySubject(@PathVariable("code") String code) {
		return ResponseEntity.ok(attendanceService.getAttendanceBySubject(code));
	}
}
