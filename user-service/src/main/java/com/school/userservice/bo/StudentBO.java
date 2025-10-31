package com.school.userservice.bo;

public class StudentBO {
	private Long id;
	private String username;
	private String email;
	private String gender;
	private String studentClass;

	public StudentBO() {

	}


	public StudentBO(Long id, String username, String email, String gender, String studentClass) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.studentClass = studentClass;
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


	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
}
