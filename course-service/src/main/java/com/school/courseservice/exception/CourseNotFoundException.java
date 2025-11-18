package com.school.courseservice.exception;

public class CourseNotFoundException extends RuntimeException{
	
    public CourseNotFoundException(String courseCode) {
		super("Course not found with code: " + courseCode);
    }

}
