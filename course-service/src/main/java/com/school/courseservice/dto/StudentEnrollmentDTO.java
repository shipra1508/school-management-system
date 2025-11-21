package com.school.courseservice.dto;

public class StudentEnrollmentDTO {

	
	private String courseCode;
    private Long studentId;

    public StudentEnrollmentDTO() {}

    public StudentEnrollmentDTO(String courseCode, Long studentId) {
        this.courseCode = courseCode;
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}

