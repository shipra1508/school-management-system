package com.school.userservice.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String username) {
        super("Student already exists with username: " + username);
    }
}
