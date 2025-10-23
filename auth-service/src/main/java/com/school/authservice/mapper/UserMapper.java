package com.school.authservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.school.authservice.dto.SignUpDTO;
import com.school.authservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "id", ignore = true)
	User toUser(SignUpDTO dto);
}
