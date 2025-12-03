package com.school.attendance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.school.attendance.bo.AttendanceBO;
import com.school.attendance.dto.AttendanceDTO;
import com.school.attendance.entity.Attendance;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

	@Mapping(target = "id", ignore = true)
	Attendance toEntity(AttendanceDTO dto);

	AttendanceBO toBO(Attendance entity);

	AttendanceDTO toDTO(AttendanceBO bo);
}
