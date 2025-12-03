package com.school.attendance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.school.attendance.bo.AttendanceBO;
import com.school.attendance.dto.AttendanceDTO;
import com.school.attendance.dto.CourseDTO;
import com.school.attendance.entity.Attendance;
import com.school.attendance.exception.AttendanceNotAllowedException;
import com.school.attendance.exception.AttendanceNotFoundException;
import com.school.attendance.mapper.AttendanceMapper;
import com.school.attendance.repository.AttendanceRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger log = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private static final String COURSE_SERVICE_URL = "http://localhost:8083/courses/";

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    public String markAttendance(String courseCode, Long teacherId, AttendanceDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Marking attendance: user='{}', roles='{}', student='{}', course='{}'",
                auth != null ? auth.getName() : "anonymous",
                auth != null ? auth.getAuthorities() : "none",
                dto.getStudentUsername(),
                courseCode
        );

        //Extracts JWT 
        String token = extractJwtToken(auth);
        if (token == null || !token.contains(".")) {
            throw new AttendanceNotAllowedException("Missing or invalid JWT token for downstream call");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        //Calls course-service
        ResponseEntity<CourseDTO[]> response = restTemplate.exchange(
                COURSE_SERVICE_URL + "findByCode/" + courseCode,
                HttpMethod.GET,
                entity,
                CourseDTO[].class
        );

        CourseDTO[] courses = response.getBody();
        if (courses == null || courses.length == 0) {
            throw new AttendanceNotFoundException("Course not found: " + courseCode);
        }

        CourseDTO course = courses[0];

        //Checks Role (i.e. teacher/admin)
        boolean teacherMatches = course.getTeacherId() != null && course.getTeacherId().equals(teacherId);
        boolean isAdmin = hasAdminRole(auth);

        if (!(teacherMatches || isAdmin)) {
            throw new AttendanceNotAllowedException("Teacher not authorized for course: " + courseCode);
        }

        //Checks student enrollment
        if (course.getStudentIds() == null || !course.getStudentIds().contains(dto.getStudentId())) {
            throw new AttendanceNotAllowedException("Student not enrolled in course: " + courseCode);
        }

        //checks for duplicate attendance
        if (attendanceRepository.existsByStudentIdAndSubjectCodeAndDate(
                dto.getStudentId(), courseCode, dto.getDate())) {
            throw new AttendanceNotAllowedException(
                    "Attendance already marked for this student on this date for course: " + courseCode
            );
        }

        //Saves attendance
        Attendance attendance = attendanceMapper.toEntity(dto);
        attendance.setSubjectCode(courseCode);
        attendanceRepository.save(attendance);

        return "Attendance marked successfully";
    }



    @Override
    public List<AttendanceDTO> getAttendanceByStudent(String studentUsername) {
        List<Attendance> records = attendanceRepository.findByStudentUsername(studentUsername);
        if (records.isEmpty()) {
            log.warn("No attendance found for student '{}'", studentUsername);
            throw new AttendanceNotFoundException("No attendance found for student: " + studentUsername);
        }

        return records.stream()
                .map(record -> attendanceMapper.toDTO(attendanceMapper.toBO(record)))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceBySubject(String subjectCode) {
        List<Attendance> records = attendanceRepository.findBySubjectCode(subjectCode);
        if (records.isEmpty()) {
            log.warn("No attendance found for subject '{}'", subjectCode);
            throw new AttendanceNotFoundException("No attendance found for subject: " + subjectCode);
        }

        return records.stream()
                .map(record -> attendanceMapper.toDTO(attendanceMapper.toBO(record)))
                .collect(Collectors.toList());
    }


    private String extractJwtToken(Authentication auth) {
        if (auth == null) {
            log.error("No authentication found in SecurityContext");
            return null;
        }

        Object credentials = auth.getCredentials();
        if (credentials instanceof String jwt) {
            return jwt;
        }

        log.error("Authentication credentials do not contain a JWT token. principal={}, credentials={}",
                auth.getPrincipal(), auth.getCredentials());
        return null;
    }

    private boolean hasAdminRole(Authentication auth) {
        return auth != null && auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}
