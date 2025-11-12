package com.school.userservice.bo;

public class AdminBO {

	private Long id;
	private String username;
	private String email;
	private String gender;
	private String phone;
	
	public AdminBO() {

	}

	public AdminBO(Long id, String username, String email, String gender, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.gender = gender;
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
