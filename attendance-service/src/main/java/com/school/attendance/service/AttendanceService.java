package com.school.attendance.service;

import java.util.List;

import com.school.attendance.dto.AttendanceDTO;

public interface AttendanceService {

	String markAttendance(String courseCode, Long teacherId, AttendanceDTO dto);

	List<AttendanceDTO> getAttendanceByStudent(String studentUsername);

	List<AttendanceDTO> getAttendanceBySubject(String studentCode);
}
