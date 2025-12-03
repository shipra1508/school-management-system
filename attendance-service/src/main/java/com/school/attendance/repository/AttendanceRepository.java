package com.school.attendance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentUsername(String studentUsername);
    List<Attendance> findBySubjectCode(String subjectCode);
    boolean existsByStudentIdAndSubjectCodeAndDate(Long studentId, String subjectCode, LocalDate date);
}
