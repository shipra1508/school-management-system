package com.school.courseservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDTO {

	@NotBlank(message = "Course name must not be blank")
	private String courseName;

	@NotBlank(message = "Course code must not be blank")
	private String courseCode;
	
	@NotNull(message = "Teacher ID must not be null")
	private Long teacherId;


	public CourseDTO() {

	}

	public CourseDTO(String courseName, String courseCode,Long teacherId) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.teacherId = teacherId;
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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}
