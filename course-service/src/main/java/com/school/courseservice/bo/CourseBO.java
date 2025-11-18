package com.school.courseservice.bo;

public class CourseBO {

	private String courseName;
	private String courseCode;

	public CourseBO() {
	}

	public CourseBO(String courseName, String courseCode) {
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
