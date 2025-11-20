package com.school.courseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.courseservice.dto.CourseDTO;
import com.school.courseservice.dto.TeacherAssignmentRequestDTO;
import com.school.courseservice.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
		CourseDTO createdCourse = courseService.createCourse(courseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
	}

	@GetMapping("listAllCourses")
	public ResponseEntity<List<CourseDTO>> listAllCourses() {
		List<CourseDTO> courses = courseService.getAllCourses();
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/findByCode/{courseCode}")
	public ResponseEntity<List<CourseDTO>> findCoursesByCode(@PathVariable("courseCode") String code) {
		List<CourseDTO> courses = courseService.findCoursesByCode(code);
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/findByName/{courseName}")
	public ResponseEntity<List<CourseDTO>> findCoursesByName(@PathVariable("courseName") String name) {
		List<CourseDTO> courses = courseService.findCoursesByName(name);
		return ResponseEntity.ok(courses);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{courseCode}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable("courseCode") String courseCode,
			@RequestBody CourseDTO updatedCourseDTO) {
		CourseDTO updated = courseService.updateCourse(courseCode, updatedCourseDTO);
		return ResponseEntity.ok(updated);
	}

	@PostMapping("/registerTeacher")
	public ResponseEntity<CourseDTO> registerTeacher(@RequestBody TeacherAssignmentRequestDTO request) {
	    CourseDTO updated = courseService.registerTeacher(request.getCourseCode(), request.getTeacherId());
	    return ResponseEntity.ok(updated);
	}

}
