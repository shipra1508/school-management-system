package com.school.attendance.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AttendanceDTO {

	@NotNull
	private Long studentId;

	@NotNull
	private String studentUsername;

	@NotNull
	private String subjectCode;

	@NotNull
	private LocalDate date;

	@NotNull
	private Boolean present;

	public AttendanceDTO() {
		super();
	}

	public AttendanceDTO(Long studentId, String studentUsername, String subjectCode, LocalDate date, Boolean present) {
		this.studentId = studentId;
		this.studentUsername = studentUsername;
		this.subjectCode = subjectCode;
		this.date = date;
		this.present = present;
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

	public Boolean getPresent() {
		return present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}

}
