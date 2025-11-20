package com.school.courseservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.courseservice.bo.CourseBO;
import com.school.courseservice.dto.CourseDTO;
import com.school.courseservice.entity.Course;
import com.school.courseservice.exception.CourseAlreadyExistsException;
import com.school.courseservice.exception.CourseNotFoundException;
import com.school.courseservice.mapper.CourseMapper;
import com.school.courseservice.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
	    courseRepository.findByCourseCode(courseDTO.getCourseCode()).ifPresent(c -> {
	        throw new CourseAlreadyExistsException(courseDTO.getCourseCode());
	    });

	    Course course = courseMapper.toEntity(courseDTO);
	    Course saved = courseRepository.save(course);

	    CourseBO bo = courseMapper.toBO(saved);
	    return courseMapper.toDTO(bo);
	}

	@Override
	public List<CourseDTO> getAllCourses() {
	    List<Course> courses = courseRepository.findAll();
	    List<CourseDTO> courseDTOs = new ArrayList<>();

	    for (Course course : courses) {
	        CourseBO bo = courseMapper.toBO(course);
	        courseDTOs.add(courseMapper.toDTO(bo));
	    }

	    return courseDTOs;
	}

	@Override
	public List<CourseDTO> findCoursesByCode(String code) {
	    List<Course> courses = courseRepository.findByCourseCodeContainingIgnoreCase(code);
	    List<CourseDTO> courseDTOs = new ArrayList<>();
	    for (Course course : courses) {
	        CourseBO bo = courseMapper.toBO(course);
	        courseDTOs.add(courseMapper.toDTO(bo));
	    }
	    return courseDTOs;
	}

	@Override
	public List<CourseDTO> findCoursesByName(String name) {
	    List<Course> courses = courseRepository.findByCourseNameContainingIgnoreCase(name);
	    List<CourseDTO> courseDTOs = new ArrayList<>();
	    for (Course course : courses) {
	        CourseBO bo = courseMapper.toBO(course);
	        courseDTOs.add(courseMapper.toDTO(bo));
	    }
	    return courseDTOs;
	}

	@Override
	public CourseDTO updateCourse(String courseCode, CourseDTO updatedCourseDTO) {
	    Course existingCourse = courseRepository.findByCourseCode(courseCode)
	            .orElseThrow(() -> new CourseNotFoundException(courseCode));

	    CourseBO incomingBO = courseMapper.toBO(updatedCourseDTO);
	    existingCourse.setCourseName(incomingBO.getCourseName());

	    Course savedCourse = courseRepository.save(existingCourse);

	    CourseBO bo = courseMapper.toBO(savedCourse);
	    return courseMapper.toDTO(bo);
	}


	@Override
	public CourseDTO registerTeacher(String courseCode, Long teacherId) {
	    Course course = courseRepository.findByCourseCode(courseCode)
	            .orElseThrow(() -> new CourseNotFoundException(courseCode));

	    course.setTeacherId(teacherId);
	    Course savedCourse = courseRepository.save(course);

	    CourseBO bo = courseMapper.toBO(savedCourse);  
	    return courseMapper.toDTO(bo);                 
	}


}
