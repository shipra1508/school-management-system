package com.school.attendance.exception;

public class AttendanceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AttendanceNotFoundException(String message) {
        super(message);
    }
}
