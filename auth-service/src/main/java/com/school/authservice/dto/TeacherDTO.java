package com.school.authservice.dto;

public class TeacherDTO {
	private String username;
	private String email;
	private String gender = "NOT_SPECIFIED";
	private String subject = "UNKNOWN";
	private String phone = "0000000000";

	public TeacherDTO() {
		super();
	}

	public TeacherDTO(String username, String email, String gender, String subject, String phone) {
		super();
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.subject = subject;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
