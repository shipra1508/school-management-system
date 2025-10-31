package com.school.userservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.school.userservice.bo.StudentBO;
import com.school.userservice.dto.StudentDTO;
import com.school.userservice.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentBO toBO(StudentDTO dto);
    StudentDTO toDTO(StudentBO bo);

    Student toEntity(StudentBO bo);
    StudentBO toBO(Student entity);

    StudentDTO toDTO(Student entity);

    // Partial update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "username", ignore = true)
    void updateEntityFromBO(StudentBO bo, @MappingTarget Student entity);
}
