package com.school.courseservice.dto;

public class TeacherAssignmentRequestDTO {

	private String courseCode;
	private Long teacherId;
	
	public TeacherAssignmentRequestDTO() {
		
	}

	public TeacherAssignmentRequestDTO(String courseCode, Long teacherId) {
		super();
		this.courseCode = courseCode;
		this.teacherId = teacherId;
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
