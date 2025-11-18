package com.school.courseservice.mapper;

import org.mapstruct.Mapper;

import com.school.courseservice.bo.CourseBO;
import com.school.courseservice.dto.CourseDTO;
import com.school.courseservice.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseBO toBO(CourseDTO dto);
    CourseDTO toDTO(CourseBO bo);

    Course toEntity(CourseBO bo);
    CourseBO toBO(Course entity);

    Course toEntity(CourseDTO dto);
    CourseDTO toDTO(Course entity);

}