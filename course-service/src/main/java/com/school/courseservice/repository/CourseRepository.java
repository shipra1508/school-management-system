package com.school.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.courseservice.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
	 Optional<Course> findByCourseCode(String courseCode);
	 List<Course> findByCourseCodeContainingIgnoreCase(String courseCode);
	 List<Course> findByCourseNameContainingIgnoreCase(String courseName);

}
