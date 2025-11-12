package com.school.userservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.school.userservice.bo.AdminBO;
import com.school.userservice.dto.AdminDTO;
import com.school.userservice.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
	
	AdminBO toBO(AdminDTO dto);

	AdminDTO toDTO(AdminBO bo);

	Admin toEntity(AdminBO bo);

	AdminBO toBO(Admin entity);

	AdminDTO toDTO(Admin entity);

	// Partial update
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "username", ignore = true)
	void updateEntityFromBO(AdminBO bo, @MappingTarget Admin entity);
}

