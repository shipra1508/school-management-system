package com.school.attendance.bo;

import java.time.LocalDate;

public class AttendanceBO {
	private Long id;
	private Long studentId;
	private String studentUsername;
	private String subjectCode;
	private LocalDate date;
	private boolean present;

	public AttendanceBO() {
	}

	public AttendanceBO(Long id, Long studentId, String studentUsername, String subjectCode, LocalDate date,
			boolean present) {
		this.id = id;
		this.studentId = studentId;
		this.studentUsername = studentUsername;
		this.subjectCode = subjectCode;
		this.date = date;
		this.present = present;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentUsername() {
		return studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}
}
