package com.school.userservice.exception;

public class StudentNotFoundException extends RuntimeException {
	
	public StudentNotFoundException(String username) {
        super("Student not found with username: " + username);
    } 

}
