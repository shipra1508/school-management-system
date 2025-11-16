package com.school.userservice.service;

import com.school.userservice.dto.TeacherDTO;

public interface TeacherService {

	TeacherDTO getTeacherByUsername(String username);

	TeacherDTO updateTeacherProfile(String username, TeacherDTO dto);

	void save(TeacherDTO teacherDTO);
	
	void deleteTeacherByUsername(String username);
}
