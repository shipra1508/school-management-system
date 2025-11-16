package com.school.userservice.exception;

public class AdminAlreadyExistsException extends RuntimeException {
    public AdminAlreadyExistsException(String username) {
        super("Admin already exists with username: " + username);
    }
}
