package com.school.attendance.exception;

public class AttendanceNotAllowedException extends RuntimeException {
    public AttendanceNotAllowedException(String message) {
        super(message);
    }
}
