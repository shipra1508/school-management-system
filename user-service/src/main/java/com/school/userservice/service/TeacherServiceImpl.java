package com.school.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.userservice.bo.TeacherBO;
import com.school.userservice.dto.TeacherDTO;
import com.school.userservice.entity.Student;
import com.school.userservice.entity.Teacher;
import com.school.userservice.exception.TeacherAlreadyExistsException;
import com.school.userservice.exception.TeacherNotFoundException;
import com.school.userservice.mapper.TeacherMapper;
import com.school.userservice.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public TeacherDTO getTeacherByUsername(String username) {
		Teacher teacher = teacherRepository.findByUsername(username)
				.orElseThrow(() -> new TeacherNotFoundException(username));
		return teacherMapper.toDTO(teacher);
	}

	@Override
	public TeacherDTO updateTeacherProfile(String username, TeacherDTO dto) {
		Teacher teacher = teacherRepository.findByUsername(username)
				.orElseThrow(() -> new TeacherNotFoundException(username));

		TeacherBO bo = teacherMapper.toBO(dto);
		teacherMapper.updateEntityFromBO(bo, teacher);

		Teacher updated = teacherRepository.save(teacher);
		return teacherMapper.toDTO(updated);
	}

	@Override
	public void save(TeacherDTO teacherDTO) {
	    if (teacherRepository.findByUsername(teacherDTO.getUsername()).isPresent()) {
	        throw new TeacherAlreadyExistsException(teacherDTO.getUsername());
	    }
		Teacher teacher = new Teacher();
		teacher.setUsername(teacherDTO.getUsername());
		teacher.setEmail(teacherDTO.getEmail()); 
		teacher.setGender(teacherDTO.getGender());
		teacher.setSubject(teacherDTO.getSubject());
		teacher.setPhone(teacherDTO.getPhone());

		System.out.println("Saving teacher: " + teacher.getUsername());
		teacherRepository.save(teacher);
	}
	
	public void deleteTeacherByUsername(String username) {
	    Teacher teacher = teacherRepository.findByUsername(username)
	        .orElseThrow(() -> new TeacherNotFoundException(username));
	    teacherRepository.delete(teacher);
	}

}
