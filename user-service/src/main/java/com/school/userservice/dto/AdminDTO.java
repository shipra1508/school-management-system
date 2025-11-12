package com.school.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AdminDTO {

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phone;

	public AdminDTO() {
		
	}

	public AdminDTO(String username, String email, String gender, String phone) {
		super();
		this.username = username;
		this.email = email;
		this.gender = gender;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
