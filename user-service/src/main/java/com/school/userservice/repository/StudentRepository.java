package com.school.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.userservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByUsername(String username);

	List<Student>findByUsernameContainingIgnoreCase(String username);
	
	List<Student> findByStudentClassContainingIgnoreCase(String studentClass);

}
