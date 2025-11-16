package com.school.userservice.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());

		ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Invalid input",
				errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(LocalDateTime.now(), 404, "Student not found", List.of(ex.getMessage())));
	}

	@ExceptionHandler(TeacherNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTeacherNotFound(TeacherNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(LocalDateTime.now(), 404, "Teacher not found", List.of(ex.getMessage())));
	}

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAdminNotFound(AdminNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(), "Admin not found", List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(AdminAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAdminAlreadyExists(AdminAlreadyExistsException ex) {
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	        .body(new ErrorResponse(LocalDateTime.now(), 409, "Admin already exists", List.of(ex.getMessage())));
	}
	@ExceptionHandler(StudentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleStudentAlreadyExists(StudentAlreadyExistsException ex) {
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	        .body(new ErrorResponse(LocalDateTime.now(), 409, "Student already exists", List.of(ex.getMessage())));
	}
	@ExceptionHandler(TeacherAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleTeacherAlreadyExists(TeacherAlreadyExistsException ex) {
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	        .body(new ErrorResponse(LocalDateTime.now(), 409, "Teacher already exists", List.of(ex.getMessage())));
	}


}
