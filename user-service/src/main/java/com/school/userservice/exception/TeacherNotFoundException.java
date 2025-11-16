package com.school.userservice.exception;

public class TeacherNotFoundException extends RuntimeException {

	public TeacherNotFoundException(String username) {
        super("Teacher not found with username: " + username);
    }

}
