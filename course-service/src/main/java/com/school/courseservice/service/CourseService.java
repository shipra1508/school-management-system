package com.school.courseservice.service;

import java.util.List;

import com.school.courseservice.dto.CourseDTO;

public interface CourseService {
	CourseDTO createCourse(CourseDTO courseDTO);
	List<CourseDTO> getAllCourses();
	List<CourseDTO> findCoursesByCode(String code);
	List<CourseDTO> findCoursesByName(String name);
    CourseDTO updateCourse(String courseCode, CourseDTO updatedCourseDTO);
    CourseDTO registerTeacher(String courseCode, Long teacherId);
	CourseDTO studentEnroll(String courseCode, Long studentId);

}
