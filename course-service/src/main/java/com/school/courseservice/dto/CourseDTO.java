package com.school.courseservice.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseDTO {

	@NotBlank(message = "Course name must not be blank")
	private String courseName;

	@NotBlank(message = "Course code must not be blank")
	private String courseCode;

	public CourseDTO() {

	}

	public CourseDTO(String courseName, String courseCode) {
		this.courseName = courseName;
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
