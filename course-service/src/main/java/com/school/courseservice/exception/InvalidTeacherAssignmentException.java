package com.school.courseservice.exception;

public class InvalidTeacherAssignmentException extends RuntimeException {
	
	public InvalidTeacherAssignmentException(Long teacherId) {
        super("User with ID " + teacherId + " is not a teacher");
    }

}
