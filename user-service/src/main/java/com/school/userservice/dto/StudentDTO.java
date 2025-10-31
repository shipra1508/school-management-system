package com.school.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentDTO {
	private Long id;

	@NotBlank(message = "userName is required")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotBlank(message = "Student class is required")
	private String studentClass;

	public StudentDTO() {

	}

	public StudentDTO(Long id,String username,String email,String gender,String studentClass) {
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
