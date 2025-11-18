package com.school.courseservice.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String code) {
        super("Course with code '" + code + "' already exists.");
    }
}

