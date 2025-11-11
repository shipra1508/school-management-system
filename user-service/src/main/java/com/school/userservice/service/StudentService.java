package com.school.userservice.service;

import java.util.List;

import com.school.userservice.dto.StudentDTO;

public interface StudentService {

	StudentDTO getStudentByUsername(String username);

	StudentDTO updateStudentProfile(String username, StudentDTO dto);

	void save(StudentDTO studentDTO);

	List<StudentDTO> searchByUsername(String username);
	
	List<StudentDTO> findByStudentClass(String studentClass);

}
