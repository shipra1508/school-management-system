package com.school.attendance.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long studentId;

	@Column(nullable = false)
	private String studentUsername;

	@Column(nullable = false)
	private String subjectCode;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private boolean present;

	public Attendance() {

	}

	public Attendance(Long studentId, String studentUsername, String subjectCode, LocalDate date, boolean present) {
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
