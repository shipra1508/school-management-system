package com.school.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TeacherDTO {

	private Long id;

	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
	private String username;

	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
	private String gender;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@Size(min = 2, max = 50, message = "Subject must be between 2 and 50 characters")
	private String subject;

	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phone;

	public TeacherDTO() {
	}

	public TeacherDTO(Long id, String username, String gender, String email, String subject, String phone) {
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.email = email;
		this.subject = subject;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
