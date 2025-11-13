package com.school.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.userservice.bo.StudentBO;
import com.school.userservice.dto.StudentDTO;
import com.school.userservice.entity.Student;
import com.school.userservice.mapper.StudentMapper;
import com.school.userservice.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentDTO getStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		return studentMapper.toDTO(student);
	}

	@Override
	public StudentDTO updateStudentProfile(String username, StudentDTO dto) {
		Student student = studentRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		StudentBO bo = studentMapper.toBO(dto); 
		studentMapper.updateEntityFromBO(bo, student);

		Student updated = studentRepository.save(student);
		return studentMapper.toDTO(updated);
	}

	@Override
	public void save(StudentDTO studentDTO) {
	    Student student = new Student();
	    student.setUsername(studentDTO.getUsername());
	    student.setEmail(studentDTO.getEmail());
	    student.setGender(studentDTO.getGender());
	    student.setStudentClass(studentDTO.getStudentClass());

	    System.out.println("Saving student: " + student.getUsername());
	    studentRepository.save(student);
	}

	@Override
	public List<StudentDTO> searchByUsername(String username) {
	    return studentRepository.findByUsernameContainingIgnoreCase(username).stream()
	            .map(student -> studentMapper.toDTO(student))
	            .collect(Collectors.toList());
	}
	
	@Override
	public List<StudentDTO> findByStudentClass(String studentClass) {
	    return studentRepository.findByStudentClassContainingIgnoreCase(studentClass).stream()
	        .map(student -> studentMapper.toDTO(student))
	        .collect(Collectors.toList());
	}
	
	public void deleteStudentByUsername(String username) {
	    Student student = studentRepository.findByUsername(username)
	        .orElseThrow(() -> new RuntimeException("Student not found: " + username));
	    studentRepository.delete(student);
	}



}
