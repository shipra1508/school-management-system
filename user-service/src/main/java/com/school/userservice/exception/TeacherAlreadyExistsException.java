package com.school.userservice.exception;

public class TeacherAlreadyExistsException extends RuntimeException {
    public TeacherAlreadyExistsException(String username) {
        super("Teacher already exists with username: " + username);
    }
}
