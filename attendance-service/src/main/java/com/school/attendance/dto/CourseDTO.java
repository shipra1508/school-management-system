package com.school.attendance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {
	
	private String courseCode;
    private String courseName;
    private Long teacherId;
    private List<Long> studentIds;
    
	public CourseDTO() {
		
	}

	public CourseDTO(String courseCode, String courseName, Long teacherId, List<Long> studentIds) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.studentIds = studentIds;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public List<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Long> studentIds) {
		this.studentIds = studentIds;
	} 

}
