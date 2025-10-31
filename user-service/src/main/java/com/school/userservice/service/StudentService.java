package com.school.userservice.service;

import com.school.userservice.dto.StudentDTO;

public interface StudentService {

	StudentDTO getStudentByUsername(String username);

	StudentDTO updateStudentProfile(String username, StudentDTO dto);

	void save(StudentDTO studentDTO);

}
