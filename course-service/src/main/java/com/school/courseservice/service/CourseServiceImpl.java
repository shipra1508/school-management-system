package com.school.courseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.courseservice.dto.CourseDTO;
import com.school.courseservice.entity.Course;
import com.school.courseservice.exception.CourseAlreadyExistsException;
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
		return courseMapper.toDTO(saved);
	}
}
