package com.school.userservice.bo;

public class TeacherBO {

	private Long id;
	private String username;
	private String gender;
	private String email;
	private String subject;
	private String phone;

	public TeacherBO() {

	}

	public TeacherBO(Long id, String username, String gender, String email, String subject, String phone) {
		super();
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
