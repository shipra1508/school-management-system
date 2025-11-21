package com.school.courseservice.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class CourseDTO {

	@NotBlank(message = "Course name must not be blank")
	private String courseName;

	@NotBlank(message = "Course code must not be blank")
	private String courseCode;
	
	private Long teacherId;

    private Set<Long> studentIds = new HashSet<>();


	public CourseDTO() {

	}

	public CourseDTO(String courseName, String courseCode,Long teacherId, Set<Long> studentIds) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.teacherId = teacherId;
		this.studentIds = studentIds;
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

	public Set<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(Set<Long> studentIds) {
		this.studentIds = studentIds;
	}
	
	

}
