package com.school.userservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.school.userservice.bo.TeacherBO;
import com.school.userservice.dto.TeacherDTO;
import com.school.userservice.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	TeacherBO toBO(TeacherDTO dto);

	TeacherDTO toDTO(TeacherBO bo);

	Teacher toEntity(TeacherBO bo);

	TeacherBO toBO(Teacher entity);

	TeacherDTO toDTO(Teacher entity);

	// Partial update
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "username", ignore = true)
	void updateEntityFromBO(TeacherBO bo, @MappingTarget Teacher entity);
}
