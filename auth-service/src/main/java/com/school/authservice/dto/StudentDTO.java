package com.school.authservice.dto;

public class StudentDTO {
	private String username;
	private String email;
	private String gender = "NOT_SPECIFIED";
	private String studentClass = "UNKNOWN";

	public StudentDTO() {
		super();

	}


	public StudentDTO(String username, String email, String gender, String studentClass) {
		super();
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.studentClass = studentClass;
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
