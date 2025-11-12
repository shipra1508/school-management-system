package com.school.authservice.dto;

public class AdminDTO {
	private String username;
	private String email;
	private String gender = "NOT_SPECIFIED";
	private String phone = "0000000000";
	
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
